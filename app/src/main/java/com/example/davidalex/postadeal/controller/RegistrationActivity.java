package com.example.davidalex.postadeal.controller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.SharedPreference.SaveSharedPreference;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;

public class RegistrationActivity extends AppCompatActivity {

    ContentValues dataContent;
    SaveSharedPreference userPreferences;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        dataContent = new ContentValues();

        dataContent.put(CustomContentProvider.USER_NAME, "user");
        dataContent.put(CustomContentProvider.USER_PASSWORD, "1");




        uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
    }

    public void registration(View view) {
        String loginName = ((EditText) findViewById(R.id.regLoginName)).getText().toString();
        String password1 = ((EditText) findViewById(R.id.regPassFirst)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.regPassSecond)).getText().toString();
        String[] selectionClause = {loginName};
        Cursor queryCursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null, null, selectionClause, null);
        if (queryCursor == null) { //there is no such login name
            if (password1.equals(password2)) {//the passwords is matching each other
                //adding the new user to the data source
                //adding login name and password to shared preferences
                dataContent.put(CustomContentProvider.USER_NAME, loginName);
                dataContent.put(CustomContentProvider.USER_PASSWORD, password1);
                uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
                userPreferences.savePreferences(this, loginName, password1);
                startActivity(new Intent(this, MainActivity.class));
            }
            else { //show error: the confirm password is wrong
                ((TextView)findViewById(R.id.regErrorMessage)).setText("the confirm password is wrong");
            }
        }
        else {
            ((TextView)findViewById(R.id.regErrorMessage)).setText("this login is already exist");
        }

    }
}
