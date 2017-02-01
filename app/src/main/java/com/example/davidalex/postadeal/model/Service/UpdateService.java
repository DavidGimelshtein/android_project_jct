package com.example.davidalex.postadeal.model.Service;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.davidalex.postadeal.controller.IProviderTasks;
import com.example.davidalex.postadeal.controller.MyAsyncTask;
import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.backend.ManagerFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by david on 06.12.2016.
 */

public class UpdateService extends Service {

    IDSManager dsManager;
    public static final String MY_LOG ="my_log";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(MY_LOG, "Service is starting...");
        //dsManager = ManagerFactory.getDsManager("ListDsManager");
        dsManager = ManagerFactory.getDsManager("RemoteDsManager");
        new MyAsyncTask(new IProviderTasks() {
            @Override
            public void doInBackground() {
                checkUpdates();
            }

            @Override
            public void onPostExecute() {

            }

            @Override
            public void onProgressUpdate(Integer... values) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
    }

    private void checkUpdates() {
        Intent actionsUpdate;
        while (true) {
            try {
                int newActivities = dsManager.checkNewActivities();
                if (newActivities > 0) {
                    Log.v(MY_LOG, "Sending new actions intent");
                    actionsUpdate = new Intent("com.example.davidalex.postadeal.newUpdates").putExtra("actions", newActivities);
                    sendBroadcast(actionsUpdate);
                }
                int newBusinesses = dsManager.checkNewBusinesses();
                if (newBusinesses > 0) {
                    Log.v(MY_LOG, "Sending new actions intent");
                    actionsUpdate = new Intent("com.example.davidalex.postadeal.newUpdates").putExtra("agencies", newBusinesses);
                    sendBroadcast(actionsUpdate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            SystemClock.sleep(10000);
        }
    }
}
