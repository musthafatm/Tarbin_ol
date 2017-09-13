
package com.iroid.tarbinol.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDisplayResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ProductDetails> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetails> getData() {
        return data;
    }

    public void setData(List<ProductDetails> data) {
        this.data = data;
    }

}
