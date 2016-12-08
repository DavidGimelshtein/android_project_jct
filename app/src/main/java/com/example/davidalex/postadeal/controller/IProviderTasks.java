package com.example.davidalex.postadeal.controller;

/**
 * Created by david on 07.12.2016.
 */

public interface IProviderTasks {
    void doInBackground();
    void onPostExecute();
    void onProgressUpdate(Integer... values);
}
