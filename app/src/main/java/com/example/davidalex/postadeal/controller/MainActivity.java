package com.example.davidalex.postadeal.controller;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.backend.ManagerFactory;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;
import com.example.davidalex.postadeal.model.datasource.ListDsManager;
import com.example.davidalex.postadeal.model.entities.Categories;
import com.example.davidalex.postadeal.model.entities.User;

import static com.example.davidalex.postadeal.model.datasource.ListDsManager.MY_LOG;

//import static com.example.davidalex.postadeal.model.datasource.ListDsManager.MY_LOG;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContentValues dataContent;
    private Uri uri;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dataContent = new ContentValues();
        //inserting  new User
        dataContent.put(CustomContentProvider.USER_NAME, "David");
        dataContent.put(CustomContentProvider.USER_PASSWORD, "DGjhjkgl845");
        dataContent.put(CustomContentProvider.USER_ID, "1");
        uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
        //inserting  new User
        dataContent.put(CustomContentProvider.USER_NAME, "Alex");
        dataContent.put(CustomContentProvider.USER_PASSWORD, "A64646ldx");
        dataContent.put(CustomContentProvider.USER_ID, "2");
        uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
        //inserting  new Business
        dataContent.put(CustomContentProvider.COMPANY_NAME, "IsraTour");
        dataContent.put(CustomContentProvider.COMPANY_ADRESS, "Tel Aviv Jabotinsky 27");
        dataContent.put(CustomContentProvider.COMPANY_TEL, "03-5555999");
        dataContent.put(CustomContentProvider.COMPANY_EMAIL, "isratour@gmal.com");
        dataContent.put(CustomContentProvider.COMPANY_WEB_SITE, "isratour.co.il");
        dataContent.put(CustomContentProvider.COMPANY_ID, "1");
        uri = getContentResolver().insert(CustomContentProvider.BUSINESS_CONTENT_URI, dataContent);
        //inserting  new Business
        dataContent.put(CustomContentProvider.COMPANY_NAME, "Ambasador");
        dataContent.put(CustomContentProvider.COMPANY_ADRESS, "Jerusalem Yaffo 27");
        dataContent.put(CustomContentProvider.COMPANY_TEL, "03-5555999");
        dataContent.put(CustomContentProvider.COMPANY_EMAIL, "isratour@gmal.com");
        dataContent.put(CustomContentProvider.COMPANY_WEB_SITE, "isratour.co.il");
        dataContent.put(CustomContentProvider.COMPANY_ID, "2");
        uri = getContentResolver().insert(CustomContentProvider.BUSINESS_CONTENT_URI, dataContent);
        //inserting  new Activity
        dataContent.put(CustomContentProvider.ACTIVITY_CATEGORY, Categories.HOTELS.toString());
        dataContent.put(CustomContentProvider.ACTIVITY_COMPANY_ID, "2");
        dataContent.put(CustomContentProvider.ACTIVITY_DESCRIPTION, "3 night at Jerusalem Ramada hotel");
        dataContent.put(CustomContentProvider.ACTIVITY_PRICE, "1200");
        dataContent.put(CustomContentProvider.ACTIVITY_PLACEMENT, "Israel");
        dataContent.put(CustomContentProvider.ACTIVITY_START_DATE, "26.12.16");
        dataContent.put(CustomContentProvider.ACTIVITY_END_DATE, "29.12.16");
        uri = getContentResolver().insert(CustomContentProvider.ACTIVITY_CONTENT_URI, dataContent);
        //get user list
        cursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null, null, null, null);
        printCursorToLog(cursor);
        //get businesses list
        cursor = getContentResolver().query(CustomContentProvider.BUSINESS_CONTENT_URI, null, null, null, null);
        printCursorToLog(cursor);
        //get activities list
        cursor = getContentResolver().query(CustomContentProvider.ACTIVITY_CONTENT_URI, null, null, null, null);
        printCursorToLog(cursor);
        //checking if there is a new Activities
        ListDsManager listDsManager = (ListDsManager)CustomContentProvider.dsManager;
        listDsManager.checkChanges();
        //checking if there is a new Activities


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

        }
    }

    void printCursorToLog(Cursor c){
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");

                    }
                    Log.d(MY_LOG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(MY_LOG, "Cursor is null");
    }

    private void addUser() {
        // Add a new student record
        dataContent.put(CustomContentProvider.USER_NAME, "David");
        dataContent.put(CustomContentProvider.USER_PASSWORD, "DGjhjkgl845");
        dataContent.put(CustomContentProvider.USER_ID, "1");

        Uri uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
        Cursor cursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null, null, null, null);

        //       Log.d(MY_LOG, uri.toString());
        //Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }
}
