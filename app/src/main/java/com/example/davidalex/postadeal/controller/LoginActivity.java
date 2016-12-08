package com.example.davidalex.postadeal.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        //  startService(new Intent(this, UpdateService.class));
        //check if fields are not empty
        //if the login name with the password are already stored in the data base then invoke main Activity
        //if not then open registration
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //check if fields are not empty
                selectionArgum = new String[]{userName.getText().toString(), userPassword.getText().toString()};
                new MyAsyncTask(new IProviderTasks() {

                    @Override
                    public void doInBackground() {
                       queryCursor = context.getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null,
                        null, selectionArgum, null);
                    }

                    @Override
                    public void onPostExecute() {
                        if (queryCursor != null)
                            startActivity(new Intent(context, MainActivity.class));//maybe it will be pass also info of the user
                        else {
                            String r = "The login name or password are incorect";
                             loginError.setText(r);
                        }
                    }

                    @Override
                    public void onProgressUpdate(Integer... values) {
                       loginProgressBar.setProgress(values[0]);
//                        //Toast toast = Toast.makeText(context, values[0].toString(), Toast.LENGTH_SHORT);
//                        //toast.show();
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

    private  void populateLoginFields() {

        if (userPreferences.getUserName(this) != null && userPreferences.getUserPassword(this) != null) {
            userName.setText(userPreferences.getUserName(this));
            userPassword.setText(userPreferences.getUserPassword(this));
        }
    }


}
