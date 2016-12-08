package com.example.davidalex.postadeal.model.datasource;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.backend.ManagerFactory;

/**
 * Created by david on 22.11.2016.
 */

public class CustomContentProvider extends ContentProvider {

    public static IDSManager dsManager = ManagerFactory.getDsManager("ListDsManager");

    public static final int USERS = 1;
    public static final int BUSINESSES = 2;
    public static final int ACTIVITIES = 3;

    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_ID = "_id";

    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_ADRESS = "company_adress";
    public static final String COMPANY_TEL = "company_tel";
    public static final String COMPANY_EMAIL = "company_email";
    public static final String COMPANY_WEB_SITE = "company_web_site";

    public static final String ACTIVITY_CATEGORY = "activity_category";
    public static final String ACTIVITY_PLACEMENT = "activity_placement";
    public static final String ACTIVITY_START_DATE = "activity_start_date";
    public static final String ACTIVITY_END_DATE = "activity_end_date";
    public static final String ACTIVITY_PRICE = "activity_price";
    public static final String ACTIVITY_DESCRIPTION = "activity_description";
    public static final String ACTIVITY_COMPANY_ID = "activity_company_id";

    static final String PROVIDER_NAME = "com.example.davidalex.postadeal.MyProvider";

    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/users");
    public static final Uri BUSINESS_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/businesses");
    public static final Uri ACTIVITY_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/activities");

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", 1);
        uriMatcher.addURI(PROVIDER_NAME, "businesses", 2);
        uriMatcher.addURI(PROVIDER_NAME, "activities", 3);
    }


    Uri uri;
    ContentValues contentValues;
    String[] projection;
    String[] selectionArgum;
    String selectionClause, sortOrder;
    Cursor result;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, final String[] projection, final String selectionClause, final String[] selectionArgum, final String sortOrder) {

        this.uri = uri;
        this.selectionArgum = selectionArgum;
        result = null;
        switch (uriMatcher.match(uri)) {
            case USERS:

                result = dsManager.getUsersList(projection, selectionClause, selectionArgum, sortOrder);


                break;
            case BUSINESSES:


                result = dsManager.getBusinessList();

                break;
            case ACTIVITIES:


                result = dsManager.getActivitiesList();

                break;
            default:
                //throw new Exception("");
        }
        return result;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        switch (uriMatcher.match(uri)) {
            case USERS:


                dsManager.addUser(contentValues);


                break;
            case BUSINESSES:

                dsManager.addBusiness(contentValues);

                break;
            case ACTIVITIES:

                dsManager.addActivity(contentValues);

                break;
            default:
                //throw new Exception("");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
