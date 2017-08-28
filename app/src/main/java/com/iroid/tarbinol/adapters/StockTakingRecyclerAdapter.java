package com.iroid.tarbinol.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.model.StockTakingModel;

import java.util.List;

/**
 * Created by Acer on 28-Aug-17.
 */

public class StockTakingRecyclerAdapter extends RecyclerView.Adapter<StockTakingRecyclerAdapter.MySecondViewHolder>{

    private List<StockTakingModel> stockTakingModelList;

    public StockTakingRecyclerAdapter(List<StockTakingModel> stockTakingModelList){
        this.stockTakingModelList = stockTakingModelList;
    }


    @Override
    public MySecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_taking_row_list, parent, false);
        return new MySecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySecondViewHolder holder, int position) {

        StockTakingModel stockTakingModel = stockTakingModelList.get(position);
        holder.tvStockPaintName.setText(stockTakingModel.getTvStockPaintName());
        holder.tvPaintDescriptionRed.setText(stockTakingModel.getTvPaintDescriptionRed());
        holder.tvLitre.setText(stockTakingModel.getTvLitre());


        holder.et_required_paint.setText(stockTakingModel.getEt_required_paint());
        holder.et_paint_inStock.setText(stockTakingModel.getEt_paint_inStock());

    }

    @Override
    public int getItemCount() {
        return stockTakingModelList.size();
    }


    public class MySecondViewHolder extends RecyclerView.ViewHolder {

        public TextView tvStockPaintName,tvPaintDescriptionRed,tvLitre;
        public EditText et_required_paint,et_paint_inStock;

        public MySecondViewHolder(View itemView) {
            super(itemView);

            tvStockPaintName = (TextView) itemView.findViewById(R.id.tvStockPaintName);
            tvPaintDescriptionRed = (TextView) itemView.findViewById(R.id.tvPaintDescriptionRed);
            tvLitre = (TextView) itemView.findViewById(R.id.tvLitre);
            et_required_paint = (EditText) itemView.findViewById(R.id.et_required_paint);
            et_paint_inStock = (EditText) itemView.findViewById(R.id.et_paint_inStock);

        }
    }
}
