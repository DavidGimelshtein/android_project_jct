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

    private static int existActivities = 0;


    @Override
    public void addUser(ContentValues userContent) {

        userList.add(new User(userContent.getAsInteger(CustomContentProvider.USER_ID),
                    userContent.getAsString(CustomContentProvider.USER_NAME),
                    userContent.getAsString(CustomContentProvider.USER_PASSWORD)));

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

        activityList.add(new Activity(Categories.valueOf(activityContent.getAsString(CustomContentProvider.ACTIVITY_CATEGORY)),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_PLACEMENT),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_START_DATE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_END_DATE),
                activityContent.getAsFloat(CustomContentProvider.ACTIVITY_PRICE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_DESCRIPTION),
                activityContent.getAsInteger(CustomContentProvider.ACTIVITY_COMPANY_ID)));
    }

    @Override
    public Boolean checkChanges() {

        if (existActivities < activityList.size())
        {
            int diff = activityList.size() - existActivities;
            existActivities = activityList.size();
            Log.d(MY_LOG, "There is " + diff + " new Activities");
            return true;
        }
        Log.d(MY_LOG, "There is no new Activities");
        return false;
    }

    @Override
    public Cursor getUsersList() {
        Log.d(MY_LOG, "in getUsersList in class ListDsManager");
        for(int i = 0; i < userList.size(); i++)
            Log.d(MY_LOG, (i + 1) + " User name: " + userList.get(i).getUserName() + "; User Password: " + userList.get(i).getUserPassword());
        return null;
    }

    @Override
    public Cursor getBusinessList() {

        Log.d(MY_LOG, "in getBusinessList in class ListDsManager");
        for(int i = 0; i < businessList.size(); i++)
            Log.d(MY_LOG, (i + 1) + " Company name: " + businessList.get(i).getCompanyName() + "; Company Adress: " + businessList.get(i).getCompanyAdress());
        return null;
    }

    @Override
    public Cursor getActivitiesList() {

        Log.d(MY_LOG, "in getActivitiesList in class ListDsManager");
        for(int i = 0; i < activityList.size(); i++)
            Log.d(MY_LOG, (i + 1) + " Activity Category: " + activityList.get(i).getActivityCategory() + "; Activity Placement: " + activityList.get(i).getActivityPlacement());
        return null;
    }

    @Override
    public void reportChanges() {

    }
}
