package com.iroid.tarbinol.model;

/**
 * Created by Acer on 06-Sep-17.
 */

public class DayItem {

    String days;
    String location;

    public DayItem(String days, String location) {
        this.location = location;
        this.days = days;

    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
