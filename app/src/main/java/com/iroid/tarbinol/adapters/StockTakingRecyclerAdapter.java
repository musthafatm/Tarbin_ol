package com.iroid.tarbinol.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.model.ProductDetails;
import com.iroid.tarbinol.model.StockTakingModel;
import com.iroid.tarbinol.ui.StockTakingActivity;

import java.util.List;

import static com.iroid.tarbinol.R.id.et_required_paint;

/**
 * Created by Acer on 28-Aug-17.
 */

public class StockTakingRecyclerAdapter extends RecyclerView.Adapter<StockTakingRecyclerAdapter.MySecondViewHolder>{

    private List<ProductDetails> productDetailsModelList;
//    public static String[] mDataset;


    public StockTakingRecyclerAdapter(List<ProductDetails> productDetailsModelList){
        this.productDetailsModelList = productDetailsModelList;
    }


    @Override
    public MySecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_taking_row_list, parent, false);
        return new MySecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySecondViewHolder holder, final int position) {

        final ProductDetails productDetailModel = productDetailsModelList.get(position);
        holder.tvStockPaintName.setText(productDetailModel.getPaintbrand());
        holder.tvPaintDescriptionRed.setText(productDetailModel.getProductname());
        holder.tvLitre.setText(productDetailModel.getPaintquantity()+" "+ productDetailModel.getPaintunit());


//        holder.et_required_paint.setText("0");
//        holder.et_required_paint.setShadowLayer(15,7,3,R.color.text_very_dark);
//        holder.et_paint_inStock.setText("0");
//
//        productDetailModel.setRequired(holder.et_required_paint);
//        productDetailModel.setInstock(holder.et_paint_inStock);

       holder.et_required_paint.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void afterTextChanged(Editable editable) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                productDetailsModelList.get(position).setRequired(charSequence.toString());
                productDetailsModelList.get(position).setInstock(charSequence.toString());

//                mDataset[position]=charSequence.toString();
//                productDetailModel.setRequired(mDataset[position]);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productDetailsModelList.size();
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
