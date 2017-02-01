package com.example.davidalex.postadeal.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.davidalex.postadeal.model.backend.IDSManager;
import com.example.davidalex.postadeal.model.entities.Activity;
import com.example.davidalex.postadeal.model.entities.Business;
import com.example.davidalex.postadeal.model.entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.util.Patterns.WEB_URL;
import static java.lang.System.in;

/**
 * Created by david on 22.11.2016.
 */

public class RemoteDsManager implements IDSManager {

    private final String User_Name = "gimelsht";
    private final String Web_Url = "http://" +  User_Name + ".vlab.jct.ac.il";
    private static int existActivities = 0;
    private static int existBusinesses = 0;

    private String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        // print result
            return response.toString();
        }
        else {
            return "";
        }
    }

    private String POST(String url, Map<String,Object> params) throws IOException {
    //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
    // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END
        int responseCode = con.getResponseCode();
        //System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else
            return "";
    }

    @Override
    public void addUser(ContentValues userContent) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("user_name", userContent.getAsString(CustomContentProvider.USER_NAME));
            params.put("user_password", userContent.getAsString(CustomContentProvider.USER_PASSWORD));
            String results = POST(Web_Url + "/addUser.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void addBusiness(ContentValues businessContent) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("company_name", businessContent.getAsString(CustomContentProvider.COMPANY_NAME));
            params.put("company_adress", businessContent.getAsString(CustomContentProvider.COMPANY_ADRESS));
            params.put("company_tel", businessContent.getAsString(CustomContentProvider.COMPANY_TEL));
            params.put("company_email", businessContent.getAsString(CustomContentProvider.COMPANY_EMAIL));
            params.put("company_web_site", businessContent.getAsString(CustomContentProvider.COMPANY_WEB_SITE));
            params.put("company_icon", businessContent.getAsString(CustomContentProvider.COMPANY_ICON));
            params.put("user_id", businessContent.getAsString(CustomContentProvider.USER_INFO));
            String results = POST(Web_Url + "/addBusiness.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void addActivity(ContentValues activityContent) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("activity_category", activityContent.getAsString(CustomContentProvider.ACTIVITY_CATEGORY));
            params.put("activity_placement", activityContent.getAsString(CustomContentProvider.ACTIVITY_PLACEMENT));
            params.put("activity_start_date", activityContent.getAsString(CustomContentProvider.ACTIVITY_START_DATE));
            params.put("activity_end_date", activityContent.getAsString(CustomContentProvider.ACTIVITY_END_DATE));
            params.put("activity_price", activityContent.getAsString(CustomContentProvider.ACTIVITY_PRICE));
            params.put("activity_description", activityContent.getAsString(CustomContentProvider.ACTIVITY_DESCRIPTION));
            params.put("activity_company_id", activityContent.getAsString(CustomContentProvider.ACTIVITY_COMPANY_ID));
            params.put("activity_image_id", activityContent.getAsString(CustomContentProvider.ACTIVITY_IMAGE_ID));
            String results = POST(Web_Url + "/addActivity.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public int checkNewActivities() {
        try {
            JSONArray array = new JSONObject(GET(Web_Url + "/isNewActivityExist.php")).getJSONArray("products");
            int count = array.getJSONObject(0).getInt("Count");
            if (existActivities != count) {
                int efresh = count - existActivities;
                existActivities = count;
                return efresh;
            }
            return 0;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public int checkNewBusinesses() {
        try {
            JSONArray array = new JSONObject(GET(Web_Url + "/isNewBusinessExist.php")).getJSONArray("products");
            int count = array.getJSONObject(0).getInt("Count");
            if (existBusinesses != count) {
                int efresh = count - existBusinesses;
                existBusinesses = count;
                return efresh;
            }
            return 0;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cursor getUsersList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {

        try {
            Map<String, Object> params = new LinkedHashMap<>();
            switch (selectionArgum.length){
                case 1:
                    params.put("user_name", selectionArgum[0]);
                    break;
                case 2:
                    params.put("user_name", selectionArgum[0]);
                    params.put("user_password", selectionArgum[1]);
                    break;
            }
            String[] colomNames = {"userID", "userName", "userPassword"};
            MatrixCursor cursor = new MatrixCursor(colomNames);
            String result = POST(Web_Url + "/getUsersList.php", params);
            if (result.contains("products")){
                JSONArray array = new JSONObject(result).getJSONArray("products");
                for (int i = 0; i < array.length(); i++) {
                JSONObject user = array.getJSONObject(i);
                cursor.addRow(new Object[]{
                        user.getInt("_id"),
                        user.getString("user_name"),
                        user.getString("user_password")
                });
                }
            }
            if (cursor.getCount() == 0)
                return null;
            else
                return cursor;
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cursor getBusinessList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {
        try {
            String[] colomNames =
                    {CustomContentProvider.USER_INFO, CustomContentProvider.COMPANY_NAME,
                            CustomContentProvider.COMPANY_ADRESS, CustomContentProvider.COMPANY_EMAIL,
                            CustomContentProvider.COMPANY_TEL, CustomContentProvider.COMPANY_WEB_SITE,
                            CustomContentProvider.COMPANY_ICON, CustomContentProvider.COMPANY_ID};
            MatrixCursor cursor = new MatrixCursor(colomNames);
            JSONArray array = new JSONObject(GET(Web_Url + "/getBusinesses.php")).getJSONArray("products");
            for (int i = 0; i < array.length(); i++) {
                JSONObject business = array.getJSONObject(i);
                cursor.addRow(new Object[]{
                        business.getInt("user_id"),
                        business.getString(CustomContentProvider.COMPANY_NAME),
                        business.getString(CustomContentProvider.COMPANY_ADRESS),
                        business.getString(CustomContentProvider.COMPANY_EMAIL),
                        business.getString(CustomContentProvider.COMPANY_TEL),
                        business.getString(CustomContentProvider.COMPANY_WEB_SITE),
                        business.getString(CustomContentProvider.COMPANY_ICON),
                        business.getInt("_id")
                });
            }
            return cursor;
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cursor getActivitiesList(String[] projection, String selectionClause, String[] selectionArgum, String sortOrder) {

        try {
            String[] colomNames = {CustomContentProvider.ACTIVITY_CATEGORY, CustomContentProvider.ACTIVITY_PLACEMENT,
                    CustomContentProvider.ACTIVITY_DESCRIPTION, CustomContentProvider.ACTIVITY_START_DATE,
                    CustomContentProvider.ACTIVITY_END_DATE, CustomContentProvider.ACTIVITY_PRICE,
                    CustomContentProvider.ACTIVITY_COMPANY_ID, CustomContentProvider.ACTIVITY_IMAGE_ID};
            MatrixCursor cursor = new MatrixCursor(colomNames);

            Map<String, Object> params = new LinkedHashMap<>();
            String result;
            if (selectionClause != null) {
                switch (selectionClause) {
                    case "rows":
                        params.put("limit", selectionArgum[0]);
                        break;
                    default:
                        break;
                }
                result = POST(Web_Url + "/getActivities.php", params);
            }
            else
                result = GET(Web_Url + "/getActivities.php");
            if (result.contains("products")) {
                JSONArray array = new JSONObject(result).getJSONArray("products");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject agency = array.getJSONObject(i);
                    cursor.addRow(new Object[]{
                            agency.getString(CustomContentProvider.ACTIVITY_CATEGORY),
                            agency.getString(CustomContentProvider.ACTIVITY_PLACEMENT),
                            agency.getString(CustomContentProvider.ACTIVITY_DESCRIPTION),
                            agency.getString(CustomContentProvider.ACTIVITY_START_DATE),
                            agency.getString(CustomContentProvider.ACTIVITY_END_DATE),
                            agency.getDouble(CustomContentProvider.ACTIVITY_PRICE),
                            agency.getInt(CustomContentProvider.ACTIVITY_COMPANY_ID),
                            agency.getInt(CustomContentProvider.ACTIVITY_IMAGE_ID)
                    });
                }
                return cursor;
            }
            else
                return null;
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void reportChanges() {

    }
}
