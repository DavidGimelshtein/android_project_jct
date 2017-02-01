package com.example.davidalex.postadeal.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;

import com.example.davidalex.postadeal.R;
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
    private static int existBusinesses = 0;

    public ListDsManager() {
        //initialize users list
        userList.add(new User("David", "1234"));
        userList.add(new User("Alex", "1234"));
        //initialize businesses list
        businessList.add(new Business(1, "Issta", "Dizengoff St 50, Tel Aviv-Yafo", "03-7777-000", "customerservice@issta.co.il", "www.issta.co.il", R.drawable.issta_icon));
        businessList.add(new Business(1, "Ambasador", "Hillel St 13, Jerusalem", "03-609-7755", "ambtours@actcom.co.il", "www.atours.co.il", R.drawable.ambasador_icon));
        businessList.add(new Business(1, "Sorento", "Ha-Sadna'ot St 4, Hertsliya", "09-9954-554", "info@sorento.co.il", "www.sorento.co.il", R.drawable.sorento_icon));
        //initialize activities list
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.australia1));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.castle_france));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.glacier_national_park_montana));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.thailand1));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.kaieteur_falls_guyana));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.kenai_fjords_national_park_alaska));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.salzburg_austria));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.saratoga_valley_california));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.thailand));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.oia_santorini_greece));
        activityList.add(new Activity(Categories.HOTELS, "Tel Aviv", "12/12/12", "12/12/12", 133, "gggdfbdfbfdbfdb", 1, R.drawable.banff_national_park_canada));
    }

    @Override
    public void addUser(ContentValues userContent) {
        Log.d(MY_LOG, "in addUser in class ListDsManager");
        userList.add(new User(userContent.getAsString(CustomContentProvider.USER_NAME),
                userContent.getAsString(CustomContentProvider.USER_PASSWORD)));
    }

    @Override
    public void addBusiness(ContentValues businessContent) {
        Log.d(MY_LOG, "in addBusiness in class ListDsManager");
        businessList.add(new Business(businessContent.getAsInteger(CustomContentProvider.USER_INFO),
                businessContent.getAsString(CustomContentProvider.COMPANY_NAME),
                businessContent.getAsString(CustomContentProvider.COMPANY_ADRESS),
                businessContent.getAsString(CustomContentProvider.COMPANY_TEL),
                businessContent.getAsString(CustomContentProvider.COMPANY_EMAIL),
                businessContent.getAsString(CustomContentProvider.COMPANY_WEB_SITE),
                businessContent.getAsInteger(CustomContentProvider.COMPANY_ICON)));
    }

    @Override
    public void addActivity(ContentValues activityContent)  {
        Log.d(MY_LOG, "in addActivity in class ListDsManager");
        activityList.add(new Activity(Categories.valueOf(activityContent.getAsString(CustomContentProvider.ACTIVITY_CATEGORY)),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_PLACEMENT),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_START_DATE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_END_DATE),
                activityContent.getAsFloat(CustomContentProvider.ACTIVITY_PRICE),
                activityContent.getAsString(CustomContentProvider.ACTIVITY_DESCRIPTION),
                activityContent.getAsInteger(CustomContentProvider.ACTIVITY_COMPANY_ID),
                activityContent.getAsInteger(CustomContentProvider.ACTIVITY_IMAGE_ID)));
    }

    @Override
    public int checkNewActivities() {

        if (existActivities < activityList.size())
        {
            int diff = activityList.size() - existActivities;
            existActivities = activityList.size();
            Log.d(MY_LOG, "There is " + diff + " new Activities");
            return diff;
        }
        Log.d(MY_LOG, "There is no new Activities");
        return 0;
    }

    @Override
    public int checkNewBusinesses() {

        if (existBusinesses < businessList.size())
        {
            int diff = businessList.size() - existBusinesses;
            existBusinesses = businessList.size();
            Log.d(MY_LOG, "There is " + diff + " new Activities");
            return diff;
        }
        Log.d(MY_LOG, "There is no new Activities");
        return 0;
    }

    @Override
    public Cursor getUsersList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {

        String[] colomNames = {"userID", "userName", "userPassword"};
        MatrixCursor cursor = new MatrixCursor(colomNames);
        String[] fsii = new String[3];
        if (selectionArgum != null) {
            String userName, userPassword;
            switch (selectionArgum.length) {
                case 1:
                    for (int i = 0; i < userList.size(); i++) {
                        userName = userList.get(i).getUserName();
                        if (userName.equals(selectionArgum[0])) {
                            fsii[0] = userList.get(i).getUserName();
                            fsii[1] = userList.get(i).getUserPassword();
                            cursor.addRow(fsii);
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < userList.size(); i++) {
                        userName = userList.get(i).getUserName();
                        userPassword = userList.get(i).getUserPassword();
                        if (userName.equals(selectionArgum[0]) && userPassword.equals(selectionArgum[1])) {
                            fsii[0] = String.valueOf(userList.get(i).getUserID());
                            fsii[1] = userList.get(i).getUserName();
                            fsii[2] = userList.get(i).getUserPassword();
                            cursor.addRow(fsii);
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        else {
            for (int i = 0; i < userList.size(); i++) {
                fsii[0] = userList.get(i).getUserName();
                fsii[1] = userList.get(i).getUserPassword();
                cursor.addRow(fsii);
            }
        }
        if (cursor.getCount() == 0)
            return null;
        else
            return cursor;
    }

    @Override
    public Cursor getBusinessList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {

        Log.d(MY_LOG, "in getBusinessList in class ListDsManager");
        String[] colomNames =
                {CustomContentProvider.USER_INFO, CustomContentProvider.COMPANY_NAME,
                        CustomContentProvider.COMPANY_ADRESS, CustomContentProvider.COMPANY_EMAIL,
                        CustomContentProvider.COMPANY_TEL, CustomContentProvider.COMPANY_WEB_SITE,
                        CustomContentProvider.COMPANY_ICON, CustomContentProvider.COMPANY_ID};
        MatrixCursor cursor = new MatrixCursor(colomNames);
        String[] fsii = new String[8];
        for(int i = 0; i < businessList.size(); i++) {
            fsii[0] = String.valueOf(businessList.get(i).getUserID());
            fsii[1] = businessList.get(i).getCompanyName();
            fsii[2] = businessList.get(i).getCompanyAdress();
            fsii[3] = businessList.get(i).getCompanyEmail();
            fsii[4] = businessList.get(i).getCompanyTel();
            fsii[5] = businessList.get(i).getCompanyWebSite();
            fsii[6] = String.valueOf(businessList.get(i).getCompanyIcon());
            fsii[7] = String.valueOf(businessList.get(i).getCompanyID());
            cursor.addRow(fsii);
        }
        return cursor;
    }

    @Override
    public Cursor getActivitiesList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {

        Log.d(MY_LOG, "in getActivitiesList in class ListDsManager");
        String[] colomNames = {CustomContentProvider.ACTIVITY_CATEGORY, CustomContentProvider.ACTIVITY_PLACEMENT,
        CustomContentProvider.ACTIVITY_DESCRIPTION, CustomContentProvider.ACTIVITY_START_DATE,
        CustomContentProvider.ACTIVITY_END_DATE, CustomContentProvider.ACTIVITY_PRICE,
        CustomContentProvider.ACTIVITY_COMPANY_ID, CustomContentProvider.ACTIVITY_IMAGE_ID};
        MatrixCursor cursor = new MatrixCursor(colomNames);
        String[] fsii = new String[8];
        for(int i = 0; i < activityList.size(); i++) {
            fsii[0] = String.valueOf(activityList.get(i).getActivityCategory());
            fsii[1] = activityList.get(i).getActivityPlacement();
            fsii[2] = activityList.get(i).getActivityDescription();
            fsii[3] = activityList.get(i).getActivityStartDate();
            fsii[4] = activityList.get(i).getActivityEndDate();
            fsii[5] = String.valueOf(activityList.get(i).getActivityPrice());
            fsii[6] = String.valueOf(activityList.get(i).getCompanyID());
            fsii[7] = String.valueOf(activityList.get(i).getImageID());
            cursor.addRow(fsii);
        }
        return cursor;
    }

    @Override
    public void reportChanges() {

    }
}