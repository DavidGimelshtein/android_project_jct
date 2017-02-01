package com.example.davidalex.postadeal.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;
import com.example.davidalex.postadeal.model.entities.Categories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class NewActionActivity extends AppCompatActivity {

    ContentValues dataContent;
    Spinner agenciesSpiner;
    ArrayAdapter<String> adapter;
    Cursor cursor;
    ArrayList<String> l;
    ProgressBar progressBar;
    private int userID;
    Uri uri;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_action_activity);
        dataContent = new ContentValues();
        userID = Integer.parseInt(getIntent().getStringExtra("USER_INFO"));
        progressBar = (ProgressBar)findViewById(R.id.newActionProgressBar);
        context = this;
        //************************************************
        //initialize a custom spinner and set it's adapter
        agenciesSpiner = (Spinner)findViewById(R.id.newActionSpn);
        //TODO must get all businesses related to the current user
        new MyAsyncTask(new IProviderTasks() {
            @Override
            public void doInBackground() {
                cursor = getContentResolver().query(CustomContentProvider.BUSINESS_CONTENT_URI, null, null, null, null);
            }
            @Override
            public void onPostExecute() {
                l = new ArrayList<>();
                if (cursor.moveToFirst()) {
                    do {
                        l.add(cursor.getString(cursor.getColumnIndex(CustomContentProvider.COMPANY_NAME)));
                    }
                    while (cursor.moveToNext());
                }
                adapter = new ArrayAdapter<String>(context, R.layout.agencies_list_group, l){
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView==null){
                            convertView = View.inflate(NewActionActivity.this, R.layout.agencies_spinner_group, null);
                        }
                        cursor.moveToPosition(position);
                        ((TextView)convertView.findViewById(R.id.companyName)).setText(cursor.getString(cursor.getColumnIndex(CustomContentProvider.COMPANY_NAME)));
                        return convertView;
                    }
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        if (convertView==null){
                            convertView = View.inflate(NewActionActivity.this, R.layout.agencies_spinner_item, null);
                        }
                        cursor.moveToPosition(position);
                        ((TextView)convertView.findViewById(R.id.companyName)).setText(cursor.getString(cursor.getColumnIndex(CustomContentProvider.COMPANY_NAME)));
                        ((ImageView)convertView.findViewById(R.id.company_spinner_icon)).setImageResource(cursor.getInt(cursor.getColumnIndex(CustomContentProvider.COMPANY_ICON)));
                        return convertView;
                    }
                };
                agenciesSpiner.setAdapter(adapter);
            }
            @Override
            public void onProgressUpdate(Integer... values) {

            }
        }).execute();



        //************************************************
    }

    public void addAction(View view) {
        //TODO must be checked if it is all fields are filled
        //TODO must be implement CATEGORY chose option
        //TODO must be implement ACTIVITY IMAGE chose option
        dataContent.put(CustomContentProvider.ACTIVITY_CATEGORY, "HOTELS");
        cursor.moveToPosition(agenciesSpiner.getSelectedItemPosition());
        int i = cursor.getInt(cursor.getColumnIndex(CustomContentProvider.COMPANY_ID));
        dataContent.put(CustomContentProvider.ACTIVITY_COMPANY_ID, cursor.getInt(cursor.getColumnIndex(CustomContentProvider.COMPANY_ID)));
        dataContent.put(CustomContentProvider.ACTIVITY_DESCRIPTION, ((EditText)findViewById(R.id.actionDescription)).getText().toString());
        dataContent.put(CustomContentProvider.ACTIVITY_PRICE, ((EditText)findViewById(R.id.actionPrice)).getText().toString());
        dataContent.put(CustomContentProvider.ACTIVITY_PLACEMENT, ((EditText)findViewById(R.id.actionPlacement)).getText().toString());
        dataContent.put(CustomContentProvider.ACTIVITY_START_DATE, ((EditText)findViewById(R.id.actionStartDate)).getText().toString());
        dataContent.put(CustomContentProvider.ACTIVITY_END_DATE, ((EditText)findViewById(R.id.actionEndDate)).getText().toString());
        dataContent.put(CustomContentProvider.ACTIVITY_IMAGE_ID, R.drawable.new_york_city);
        new MyAsyncTask(new IProviderTasks() {
            @Override
            public void doInBackground() {
                uri = getContentResolver().insert(CustomContentProvider.ACTIVITY_CONTENT_URI, dataContent);
            }
            @Override
            public void onPostExecute() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "The new Activity was successfuly create", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onProgressUpdate(Integer... values) {
                progressBar.setProgress(values[0]);
            }
        }).execute();
    }
}
