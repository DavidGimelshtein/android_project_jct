package com.example.davidalex.postadeal.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.SharedPreference.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText userPassword;

    SaveSharedPreference userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        userName = (EditText)findViewById(R.id.user_name);
        userPassword = (EditText)findViewById(R.id.user_password);

        userName.setText(userPreferences.getUserName(this));
        userPassword.setText(userPreferences.getUserPassword(this));


    }

    public void onClick(View view) {

        userPreferences.savePreferences(this, userName.getText().toString(), userPassword.getText().toString());
    }
}
