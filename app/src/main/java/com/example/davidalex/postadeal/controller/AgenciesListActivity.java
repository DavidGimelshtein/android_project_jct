package com.example.davidalex.postadeal.controller;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;

public class AgenciesListActivity extends AppCompatActivity {

    ExpandableListView agenciesList;
    WebView browser;
    TextView agencyTel, agencyEmail, agencyAdress, agencyURL;
    Cursor userCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_list);
        browser = (WebView)findViewById(R.id.browser);
        browser.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                browser.setVisibility(View.GONE);
                return false;
            }
        });
        agenciesList = (ExpandableListView)findViewById(R.id.agencies_list);
        //TODO must return all business related to the current user
        new MyAsyncTask(new IProviderTasks() {
            @Override
            public void doInBackground() {
                userCursor = getContentResolver().query(CustomContentProvider.BUSINESS_CONTENT_URI, null, null, null, null);
            }

            @Override
            public void onPostExecute() {
                agenciesList.setAdapter(new BaseExpandableListAdapter() {
                    @Override
                    public int getGroupCount() {
                        return userCursor.getCount();
                    }

                    @Override
                    public int getChildrenCount(int groupPosition) {
                        return 1;
                    }

                    @Override
                    public Object getGroup(int groupPosition) {
                        return null;
                    }

                    @Override
                    public Object getChild(int groupPosition, int childPosition) {
                        return null;
                    }

                    @Override
                    public long getGroupId(int groupPosition) {
                        return groupPosition;
                    }

                    @Override
                    public long getChildId(int groupPosition, int childPosition) {
                        return 0;
                    }

                    @Override
                    public boolean hasStableIds() {
                        return false;
                    }

                    @Override
                    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                        View listHeader = getLayoutInflater().inflate(R.layout.agencies_list_group, parent, false);
                        userCursor.moveToPosition(groupPosition);
                        ((TextView)listHeader.findViewById(R.id.companyName)).setText(userCursor.getString(userCursor.getColumnIndex(CustomContentProvider.COMPANY_NAME)));
                        return listHeader;
                    }

                    @Override
                    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                        View listItem = getLayoutInflater().inflate(R.layout.agencies_list_item, parent, false);
                        userCursor.moveToPosition(groupPosition);
                        ((ImageView)listItem.findViewById(R.id.agencies_icon)).setImageResource(userCursor.getInt(userCursor.getColumnIndex(CustomContentProvider.COMPANY_ICON)));
                        agencyTel = ((TextView)listItem.findViewById(R.id.companyTel));
                        agencyTel.setText(userCursor.getString(userCursor.getColumnIndex(CustomContentProvider.COMPANY_TEL)));
                        agencyTel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String toDial="tel:"+agencyTel.getText().toString();
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
                            }
                        });
                        agencyAdress = ((TextView)listItem.findViewById(R.id.companyAdress));
                        agencyAdress.setText(userCursor.getString(userCursor.getColumnIndex(CustomContentProvider.COMPANY_ADRESS)));
                        agencyAdress.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String cordinate = "geo:0,0?q=" + agencyAdress.getText().toString().replace(" ", "+").replace("\n", "+");
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(cordinate)));
                            }
                        });
                        agencyEmail = ((TextView)listItem.findViewById(R.id.companyEmail));
                        agencyEmail.setText(userCursor.getString(userCursor.getColumnIndex(CustomContentProvider.COMPANY_EMAIL)));
                        agencyEmail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                        emailIntent.setData(Uri.parse("mailto:"));
//                        emailIntent.setType("*/*");
//                        emailIntent.putExtra(Intent.EXTRA_EMAIL, agencyEmail.getText().toString());
//                        startActivity(new Intent(Intent.ACTION_SENDTO));
                                String email="mailto:" + agencyEmail.getText().toString();
                                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(email)));
                            }
                        });
                        agencyURL = ((TextView)listItem.findViewById(R.id.companyURL));
                        agencyURL.setText(userCursor.getString(userCursor.getColumnIndex(CustomContentProvider.COMPANY_WEB_SITE)));
                        agencyURL.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String url = "http://" + ((TextView)v).getText().toString() + "/";
                                browser.getSettings().setJavaScriptEnabled(true);
                                browser.loadUrl(url);
                            }
                        });
                        return listItem;
                    }

                    @Override
                    public boolean isChildSelectable(int groupPosition, int childPosition) {
                        return true;
                    }
                });
            }

            @Override
            public void onProgressUpdate(Integer... values) {
            }
        }).execute();
    }
}
