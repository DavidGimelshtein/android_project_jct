package com.example.davidalex.postadeal.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.entities.Activity;
import com.example.davidalex.postadeal.model.entities.Business;
import com.example.davidalex.postadeal.model.entities.User;

import java.util.List;

/**
 * Created by david on 22.11.2016.
 */

public class RemoteDsManager implements IDSManager {
    @Override
    public void addUser(ContentValues userContent) {

    }

    @Override
    public void addBusiness(ContentValues businessContent) {

    }

    @Override
    public void addActivity(ContentValues activityContent) {

    }

    @Override
    public Boolean checkChanges() {
        return null;
    }

    @Override
    public Cursor getUsersList() {
        return null;
    }

    @Override
    public Cursor getBusinessList() {
        return null;
    }

    @Override
    public Cursor getActivitiesList() {
        return null;
    }

    @Override
    public void reportChanges() {

    }
}
