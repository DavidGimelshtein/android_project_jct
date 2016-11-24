package com.example.davidalex.postadeal.model.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by david on 23.11.2016.
 */

public class SaveSharedPreference {

    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private String userPassword;

    private static SharedPreferences sharedPreferences;

    public static void savePreferences(Context context, String userName, String userPassword) {

        sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, userName);
        editor.putString(USER_PASSWORD, userPassword);
        editor.commit();
    }

    public static String getUserName(Context context) {

        sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, "");
    }

    public static String getUserPassword(Context context) {

        sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_PASSWORD, "");
    }

}
