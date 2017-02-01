package com.example.davidalex.postadeal.controller;

import android.os.AsyncTask;

/**
 * Created by david on 08.12.2016.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    IProviderTasks providerTasks;

    public MyAsyncTask(IProviderTasks providerTasks) {
        this.providerTasks = providerTasks;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        providerTasks.doInBackground();
        for (int i = 1; i < 11; i++) {
            try {
                Thread.sleep(10);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //super.onPostExecute(result);
        providerTasks.onPostExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        //super.onProgressUpdate(values);
        providerTasks.onProgressUpdate(values);
    }
}

