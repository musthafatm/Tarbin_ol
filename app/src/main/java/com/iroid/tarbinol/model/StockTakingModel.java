package com.iroid.tarbinol.model;

/**
 * Created by Acer on 28-Aug-17.
 */

public class StockTakingModel {

    private String tvStockPaintName, tvPaintDescriptionRed, tvLitre;
    private String et_required_paint, et_paint_inStock;

    public StockTakingModel(){
    }

    public  StockTakingModel(String tvStockPaintName, String tvPaintDescriptionRed, String tvLitre, String et_required_paint,

                             String et_paint_inStock){

        this.tvStockPaintName = tvStockPaintName;
        this.tvPaintDescriptionRed = tvPaintDescriptionRed;
        this.tvLitre = tvLitre;
        this.et_required_paint = et_required_paint;
        this.et_paint_inStock = et_paint_inStock;
    }




    public String getTvStockPaintName() {
        return tvStockPaintName;
    }

    public void setTvStockPaintName(String tvStockPaintName) {
        this.tvStockPaintName = tvStockPaintName;
    }

    public String getTvPaintDescriptionRed() {
        return tvPaintDescriptionRed;
    }

    public void setTvPaintDescriptionRed(String tvPaintDescriptionRed) {
        this.tvPaintDescriptionRed = tvPaintDescriptionRed;
    }

    public String getTvLitre() {
        return tvLitre;
    }

    public void setTvLitre(String tvLitre) {
        this.tvLitre = tvLitre;
    }

    public String getEt_required_paint() {
        return et_required_paint;
    }

    public void setEt_required_paint(String et_required_paint) {
        this.et_required_paint = et_required_paint;
    }

    public String getEt_paint_inStock() {
        return et_paint_inStock;
    }

    public void setEt_paint_inStock(String et_paint_inStock) {
        this.et_paint_inStock = et_paint_inStock;
    }
}
