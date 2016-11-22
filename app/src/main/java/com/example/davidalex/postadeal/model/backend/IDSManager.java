package com.example.davidalex.postadeal.model.backend;

import android.content.ContentValues;

import com.example.davidalex.postadeal.model.entities.Activity;
import com.example.davidalex.postadeal.model.entities.Business;
import com.example.davidalex.postadeal.model.entities.User;

import java.util.List;

/**
 * Created by david on 22.11.2016.
 */

public interface IDSManager {

    void addUser(ContentValues userContent);
    void addBusiness(ContentValues businessContent);
    void addActivity(ContentValues activityContent);
    Boolean checkChanges();
    List<User> getUsersList();
    List<Business> getBusinessList();
    List<Activity> getActivitiesList();
    void reportChanges();
}
