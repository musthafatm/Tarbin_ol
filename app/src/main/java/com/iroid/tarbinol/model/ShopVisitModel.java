package com.iroid.tarbinol.model;

/**
 * Created by Acer on 25-Aug-17.
 */

public class ShopVisitModel {

    private String dashBoardShopName, dashBoardLocation;

    private boolean isPlaced;

    public ShopVisitModel(){
    }

    public ShopVisitModel(String dashBoardShopName, String dashBoardLocation){

        this.dashBoardShopName = dashBoardShopName;
        this.dashBoardLocation = dashBoardLocation;
    }

    public String getDashBoardShopName() {
        return dashBoardShopName;
    }

//    public void setDashBoardShopName(String dashBoardShopName) {
//        this.dashBoardShopName = dashBoardShopName;
//    }

    public String getDashBoardLocation() {
        return dashBoardLocation;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

//    public void setDashBoardLocation(String dashBoardLocation) {
//        this.dashBoardLocation = dashBoardLocation;
//    }
}
