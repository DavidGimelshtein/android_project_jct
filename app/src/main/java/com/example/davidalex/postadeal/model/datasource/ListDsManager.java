package com.example.davidalex.postadeal.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.entities.Activity;
import com.example.davidalex.postadeal.model.entities.Business;
import com.example.davidalex.postadeal.model.entities.Categories;
import com.example.davidalex.postadeal.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 22.11.2016.
 */

public class ListDsManager implements IDSManager {

    public static final String MY_LOG ="my_log" ;
    private List<User> userList = new ArrayList<>();
    private List<Business> businessList = new ArrayList<>();
    private List<Activity> activityList = new ArrayList<>();


    @Override
    public void addUser(ContentValues userContent) {

        try {
            userList.add(new User(userContent.getAsInteger(CustomContentProvider.USER_ID),
                    userContent.getAsString(CustomContentProvider.USER_NAME),
                    userContent.getAsString(CustomContentProvider.USER_PASSWORD)));
            Log.d(MY_LOG , "add user in ListDsManager everything good");
        } catch (Exception e) {
            Log.d(MY_LOG , "add user in ListDsManager -- something wrong");
        }
    }

    @Override
    public void addBusiness(ContentValues businessContent) {

       businessList.add(new Business(businessContent.getAsInteger(CustomContentProvider.COMPANY_ID),
               businessContent.getAsString(CustomContentProvider.COMPANY_NAME),
               businessContent.getAsString(CustomContentProvider.COMPANY_ADRESS),
               businessContent.getAsString(CustomContentProvider.COMPANY_TEL),
               businessContent.getAsString(CustomContentProvider.COMPANY_EMAIL),
               businessContent.getAsString(CustomContentProvider.COMPANY_WEB_SITE)));
    }

    @Override
    public void addActivity(ContentValues activityContent) {

        activityList.add(new Activity((Categories)activityContent.get(CustomContentProvider.ACTIVITY_CATEGORY),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_PLACEMENT),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_START_DATE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_END_DATE),
                activityContent.getAsFloat(CustomContentProvider.ACTIVITY_PRICE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_DESCRIPTION),
                activityContent.getAsInteger(CustomContentProvider.ACTIVITY_COMPANY_ID)));
    }

    @Override
    public Boolean checkChanges() {
        return null;
    }

    @Override
    public Cursor getUsersList() {
        Log.d(MY_LOG, "in getUsersList in class ListDsManager");
        for(int i = 0; i < userList.size(); i++)
            Log.d(MY_LOG, userList.get(i).getUserName() + " " + (i+1));
        return null;
    }

    @Override
    public Cursor getBusinessList() {

        return (Cursor)businessList;
    }

    @Override
    public Cursor getActivitiesList() {

        return (Cursor)activityList;
    }

    @Override
    public void reportChanges() {

    }
}
