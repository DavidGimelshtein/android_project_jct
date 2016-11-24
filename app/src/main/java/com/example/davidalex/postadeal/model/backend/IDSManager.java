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
    Boolean checkChanges();
    Cursor getUsersList();
    Cursor getBusinessList();
    Cursor getActivitiesList();
    void reportChanges();
}
