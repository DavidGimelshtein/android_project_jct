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
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;
import com.example.davidalex.postadeal.model.entities.User;

import static com.example.davidalex.postadeal.model.datasource.ListDsManager.MY_LOG;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContentValues dataContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        dataContent = new ContentValues();
        addUser();
        addUser();
        Cursor cursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI, null, null, null,null);
        //с курсором проблема, класс ListDsManager функция getUsersList не может конвертировать
        //arrylist в курсор, похоже
 //       printCursorToLog(cursor);
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
        dataContent.put(CustomContentProvider.USER_NAME,"David");
        dataContent.put(CustomContentProvider.USER_PASSWORD, "DGjhjkgl845");
        dataContent.put(CustomContentProvider.USER_ID, "1");

        Uri uri = getContentResolver().insert(CustomContentProvider.USER_CONTENT_URI , dataContent);
        Cursor cursor = getContentResolver().query(CustomContentProvider.USER_CONTENT_URI ,null, null, null,null);

 //       Log.d(MY_LOG, uri.toString());
        //Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }
}
