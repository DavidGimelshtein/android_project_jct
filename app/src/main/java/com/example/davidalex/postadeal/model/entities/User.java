package com.example.davidalex.postadeal.model.entities;

/**
 * Created by david on 22.11.2016.
 */

public class User {

    private static int userID = 0;
    private String userName;
    private String userPassword;

    public User(String userName, String userPassword) {
        this.userID++;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
