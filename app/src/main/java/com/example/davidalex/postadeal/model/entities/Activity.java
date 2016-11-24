package com.example.davidalex.postadeal.model.entities;

/**
 * Created by david on 22.11.2016.
 */

public class Activity {

    private Categories activityCategory;
    private String activityPlacement;
    private String activityStartDate;
    private String activityEndDate;
    private float activityPrice;
    private String activityDescription;
    private int companyID;

    public Activity(Categories activityCategory, String activityPlacement, String activityStartDate, String activityEndDate,
                    float activityPrice, String activityDescription, int companyID) {
        this.activityCategory = activityCategory;
        this.activityPlacement = activityPlacement;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.activityPrice = activityPrice;
        this.activityDescription = activityDescription;
        this.companyID = companyID;
    }

    public Categories getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(Categories activityCategory) {
        this.activityCategory = activityCategory;
    }

    public String getActivityPlacement() {
        return activityPlacement;
    }

    public void setActivityPlacement(String activityPlacement) {
        this.activityPlacement = activityPlacement;
    }

    public String getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public float getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(float activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
