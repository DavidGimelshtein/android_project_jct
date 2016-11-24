package com.example.davidalex.postadeal.model.entities;

/**
 * Created by david on 22.11.2016.
 */

public class Business {

    private int companyID;
    private String companyName;
    private String companyAdress;
    private String companyTel;
    private String companyEmail;
    private String companyWebSite;

    public Business(int companyID, String companyName, String companyAdress, String companyTel, String companyEmail,
                    String companyWebSite) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAdress = companyAdress;
        this.companyTel = companyTel;
        this.companyEmail = companyEmail;
        this.companyWebSite = companyWebSite;
    }

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
