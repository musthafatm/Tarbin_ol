
package com.iroid.tarbinol.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetails {

    @SerializedName("shopname")
    @Expose
    private String shopname;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("paintquantity")
    @Expose
    private String paintquantity;
    @SerializedName("paintunit")
    @Expose
    private String paintunit;
    @SerializedName("paintbrand")
    @Expose
    private String paintbrand;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPaintquantity() {
        return paintquantity;
    }

    public void setPaintquantity(String paintquantity) {
        this.paintquantity = paintquantity;
    }

    public String getPaintunit() {
        return paintunit;
    }

    public void setPaintunit(String paintunit) {
        this.paintunit = paintunit;
    }

    public String getPaintbrand() {
        return paintbrand;
    }

    public void setPaintbrand(String paintbrand) {
        this.paintbrand = paintbrand;
    }

}
