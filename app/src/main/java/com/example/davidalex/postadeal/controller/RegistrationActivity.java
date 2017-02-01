package com.example.davidalex.postadeal.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.SharedPreference.SaveSharedPreference;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;

public class RegistrationActivity extends AppCompatActivity {

    ContentValues dataContent;
    ProgressBar regProgressBar;
    Context context;
    Uri uri;
    String[] selectionClause;
    Cursor queryCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.registration_activity);
        regProgressBar = (ProgressBar)findViewById(R.id.regProgressBar);
        regProgressBar.setProgress(0);
        dataContent = new ContentValues();
    }

    public void registration(View view) {
        final String loginName = ((EditText) findViewById(R.id.regLoginName)).getText().toString();
        final String password1 = ((EditText) findViewById(R.id.regPassFirst)).getText().toString();
        final String password2 = ((EditText) findViewById(R.id.regPassSecond)).getText().toString();
        selectionClause = new String[]{loginName};
        new MyAsyncTask(new IProviderTasks() {
            @Override
            public void doInBackground() {
                queryCursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null, null, selectionClause, null);
            }

            @Override
            public void onPostExecute() {
                if (queryCursor == null) { //there is no such login name
                    if (password1.equals(password2)) {//the passwords is matching each other
                        //adding the new user to the data source
                        //adding login name and password to shared preferences
                        dataContent.put(CustomContentProvider.USER_NAME, loginName);
                        dataContent.put(CustomContentProvider.USER_PASSWORD, password1);
                        new MyAsyncTask(new IProviderTasks() {
                            @Override
                            public void doInBackground() {
                                uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI, dataContent);
                            }
                            @Override
                            public void onPostExecute() {
                                //userPreferences.savePreferences(this, loginName, password1);
                                startActivity(new Intent(context, LoginActivity.class).putExtra("User_Info", new String[]{loginName, password1}));
                            }
                            @Override
                            public void onProgressUpdate(Integer... values) {
                                regProgressBar.setProgress(values[0]);
                            }
                        }).execute();
                    }
                    else { //show error: the confirm password is wrong
                        ((TextView)findViewById(R.id.regErrorMessage)).setText("the confirm password is wrong");
                    }
                }
                else {
                    ((TextView)findViewById(R.id.regErrorMessage)).setText("this Login Name is already exist");
                }

            }

            @Override
            public void onProgressUpdate(Integer... values) {
            }
        }).execute();

    }
}
