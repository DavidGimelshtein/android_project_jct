package com.example.davidalex.postadeal.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;
import com.example.davidalex.postadeal.model.entities.Categories;
import com.example.davidalex.postadeal.model.entities.User;

//import static com.example.davidalex.postadeal.model.datasource.ListDsManager.MY_LOG;


public class MainActivity extends AppCompatActivity {

    private ContentValues dataContent;
    private String[] userInfo;
    ProgressBar progressBar;
    private Uri uri;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        userInfo = getIntent().getStringArrayExtra("USER_INFO");
        ((TextView) findViewById(R.id.userInfo)).setText(" " + userInfo[0]);
    }

    public void clickHandler(View view) {

        switch (view.getId()) {
            case R.id.newBusiness:
                showBusinessDialog();
                break;
            case R.id.newAction:
                //TODO must be checked if there is any businesses registered by current user
                startActivity(new Intent(this, NewActionActivity.class).putExtra("USER_INFO", userInfo[1]));
                break;
            case R.id.businessList:
                startActivity(new Intent(this, AgenciesListActivity.class).putExtra("USER_INFO", userInfo[1]));
                break;
            case R.id.actionList:
                startActivity(new Intent(this, DealsListActivity.class));
                break;
            default:
                break;
        }
    }

    private void showBusinessDialog() {
        //new add business dialog
        //create an dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogView = inflater.inflate(R.layout.new_business_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        //initialize an dialog elements
        progressBar = (ProgressBar)dialogView.findViewById(R.id.dialogProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        final EditText companyName = (EditText)dialogView.findViewById(R.id.companyName);
        final EditText companyAdress = (EditText)dialogView.findViewById(R.id.companyAdress);
        final EditText companyTel = (EditText)dialogView.findViewById(R.id.companyTel);
        final EditText companyEmail = (EditText)dialogView.findViewById(R.id.companyEmail);
        final EditText companyWebSite = (EditText)dialogView.findViewById(R.id.companyWebSite);
        //create to "addButton" on click listener
        ((Button)dialogView.findViewById(R.id.dialog_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String month, year, total;
                //Cursor totalCursor;
                //String[] selectedColumns = new String[]{"sum(price) as total"};
                //get current date
//                simpleDateFormat = new SimpleDateFormat("d/M/yy");
//                currentDate = simpleDateFormat.format(new Date());
//                month = currentDate.substring(currentDate.indexOf("/") + 1, currentDate.lastIndexOf("/"));
//                year = currentDate.substring(currentDate.lastIndexOf("/") + 1);

                //get content from dialog elements
                dataContent = new ContentValues();
                dataContent.put(CustomContentProvider.USER_INFO, userInfo[1]);
                dataContent.put(CustomContentProvider.COMPANY_NAME, companyName.getText().toString());
                dataContent.put(CustomContentProvider.COMPANY_ADRESS, companyAdress.getText().toString());
                dataContent.put(CustomContentProvider.COMPANY_TEL, companyTel.getText().toString());
                dataContent.put(CustomContentProvider.COMPANY_EMAIL, companyEmail.getText().toString());
                dataContent.put(CustomContentProvider.COMPANY_WEB_SITE, companyWebSite.getText().toString());
                dataContent.put(CustomContentProvider.COMPANY_ICON, R.drawable.travel_icon);
                new MyAsyncTask(new IProviderTasks() {
                    @Override
                    public void doInBackground() {
                        uri = getContentResolver().insert(CustomContentProvider.BUSINESS_CONTENT_URI, dataContent);
                    }
                    @Override
                    public void onPostExecute() {
                        progressBar.setVisibility(View.GONE);
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "The new business was successfuly create", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onProgressUpdate(Integer... values) {
                        progressBar.setProgress(values[0]);
                    }
                }).execute();
            }
        });
        dialog.getWindow().setLayout(300, 300);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show(); // show the dialog
    }
}
