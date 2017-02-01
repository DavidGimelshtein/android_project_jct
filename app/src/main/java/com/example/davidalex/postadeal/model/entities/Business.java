package com.example.davidalex.postadeal.model.entities;

/**
 * Created by david on 22.11.2016.
 */

public class Business {

    private static int companyID;
    private int userID;
    private String companyName;
    private String companyAdress;
    private String companyTel;
    private String companyEmail;
    private String companyWebSite;
    private int companyIcon;

    public Business(int userID, String companyName, String companyAdress, String companyTel, String companyEmail,
                    String companyWebSite, int companyIcon) {
        companyID++;
        this.userID = userID;
        this.companyName = companyName;
        this.companyAdress = companyAdress;
        this.companyTel = companyTel;
        this.companyEmail = companyEmail;
        this.companyWebSite = companyWebSite;
        this.companyIcon = companyIcon;
    }

    public int getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(int companyIcon) {
        this.companyIcon = companyIcon;
    }

    public int getUserID() {    return userID;    }

    public void setUserID(int userID) {  this.userID = userID;    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyWebSite() {
        return companyWebSite;
    }

    public void setCompanyWebSite(String companyWebSite) {
        this.companyWebSite = companyWebSite;
    }
}
