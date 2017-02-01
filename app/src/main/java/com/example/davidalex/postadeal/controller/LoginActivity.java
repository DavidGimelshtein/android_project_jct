package com.example.davidalex.postadeal.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.Service.UpdateService;
import com.example.davidalex.postadeal.model.SharedPreference.SaveSharedPreference;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText userPassword;
    TextView loginError;
    String[] selectionArgum;
    Cursor queryCursor;
    Context context;
    ProgressBar loginProgressBar;
    SaveSharedPreference userPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = this;
        userName = (EditText)findViewById(R.id.user_name);
        userPassword = (EditText)findViewById(R.id.user_password);
        loginError = (TextView)findViewById(R.id.loginError);
        loginProgressBar = (ProgressBar)findViewById(R.id.loginProgressBar);
        loginProgressBar.setProgress(0);
        populateLoginFields();
        startService(new Intent(this, UpdateService.class));
        //check if fields are not empty
        //if the login name with the password are already stored in the data base then invoke main Activity
        //if not then open registration
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //check if fields are not empty
                selectionArgum = new String[]{userName.getText().toString(), userPassword.getText().toString()};
                loginProgressBar.setVisibility(View.VISIBLE);
                new MyAsyncTask(new IProviderTasks() {
                    @Override
                    public void doInBackground() {
                        //return id of user if exist
                        queryCursor = context.getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null,
                                null, selectionArgum, null);
                    }

                    @Override
                    public void onPostExecute() {
                        if (queryCursor != null) {
                            if (((CheckBox) findViewById(R.id.loginCheckbox)).isChecked()) {
                                //saved preferences
                                userPreferences.savePreferences(context, selectionArgum[0], selectionArgum[1]);
                            }
                            queryCursor.moveToFirst();
                            selectionArgum[1] = queryCursor.getString(0);
                            startActivity(new Intent(context, MainActivity.class).putExtra("USER_INFO", selectionArgum));
                        } else {
                            loginError.setText("The login name or password are incorect");
                            loginProgressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onProgressUpdate(Integer... values) {
                        loginProgressBar.setProgress(values[0]);
                    }
                }).execute();
                break;
            case R.id.register_button:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            default:
                break;
        }
    }

    private void populateLoginFields() {

        Intent registrationIntent = getIntent();
        String[] userInfo = registrationIntent.getStringArrayExtra("User_Info");
        if (userInfo == null) {

            if (userPreferences.getUserName(this) != null && userPreferences.getUserPassword(this) != null) {
                userName.setText(userPreferences.getUserName(this));
                userPassword.setText(userPreferences.getUserPassword(this));
            }
        }
        else {
            userName.setText(userInfo[0]);
            userPassword.setText(userInfo[1]);
        }
    }
}
