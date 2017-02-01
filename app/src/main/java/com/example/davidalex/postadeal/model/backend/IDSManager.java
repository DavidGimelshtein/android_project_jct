package com.example.davidalex.postadeal.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by david on 22.11.2016.
 */

public interface IDSManager {

    void addUser(ContentValues userContent);
    void addBusiness(ContentValues businessContent);
    void addActivity(ContentValues activityContent);
    int checkNewActivities();
    int checkNewBusinesses();
    Cursor getUsersList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder);
    Cursor getBusinessList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder);
    Cursor getActivitiesList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder);
    void reportChanges();
}
