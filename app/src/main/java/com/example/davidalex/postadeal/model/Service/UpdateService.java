package com.example.davidalex.postadeal.model.Service;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by david on 06.12.2016.
 */

public class UpdateService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //Log.v(TAG, "Service is starting...");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(final Void... params) {
                //checkForDBUpdates();
                return null;
            }
        }.execute();//executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);

    }

//    private void checkUpdates() {
//        Intent agenciesUpdate, tripsUpdate;
//        tripsUpdate = new Intent("com.example.ezras.newUpdates").putExtra("table", 't');
//        agenciesUpdate = new Intent("com.example.ezras.newUpdates").putExtra("table", 'a');
//
//        while (true) {
//
//            try {
//                if (manager.newAgenciesUpdates()) {
//                    Log.v(TAG, "Sending newAgencies intent...");
//                    sendBroadcast(agenciesUpdate);
//                }
//                if (manager.newTripsUpdates()) {
//                    Log.v(TAG, "Sending newTrips intent...");
//                    sendBroadcast(tripsUpdate);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            SystemClock.sleep(10000);
//        }
//    }
}
