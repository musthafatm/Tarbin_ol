
package com.iroid.tarbinol.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shopname")
    @Expose
    private String shopname;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("checkingdate")
    @Expose
    private String checkingdate;
    @SerializedName("checkingtime")
    @Expose
    private String checkingtime;
    @SerializedName("shop_id")
    @Expose
    private String shopId;
    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("employeeid")
    @Expose
    private String employeeid;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCheckingdate() {
        return checkingdate;
    }

    public void setCheckingdate(String checkingdate) {
        this.checkingdate = checkingdate;
    }

    public String getCheckingtime() {
        return checkingtime;
    }

    public void setCheckingtime(String checkingtime) {
        this.checkingtime = checkingtime;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
