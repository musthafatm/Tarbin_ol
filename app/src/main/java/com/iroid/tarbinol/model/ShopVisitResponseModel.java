
package com.iroid.tarbinol.model;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopVisitResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ArrayList<ShopDetails> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ShopDetails> getData() {
        return data;
    }

    public void setData(ArrayList<ShopDetails> data) {
        this.data = data;
    }

}
