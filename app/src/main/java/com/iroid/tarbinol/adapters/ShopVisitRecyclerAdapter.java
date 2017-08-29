package com.iroid.tarbinol.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.model.ShopVisitModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 25-Aug-17.
 */

public class ShopVisitRecyclerAdapter extends RecyclerView.Adapter<ShopVisitRecyclerAdapter.MyViewHolder> {

    private List<ShopVisitModel> shopVisitModelList;
    private OnItemClickListener onItemClickListener;

    public ShopVisitRecyclerAdapter(List<ShopVisitModel> shopVisitModelList){this.shopVisitModelList = shopVisitModelList;}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_visit_row_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ShopVisitModel shopVisitModel = shopVisitModelList.get(position);
        holder.dashBoardShopName.setText(shopVisitModel.getDashBoardShopName());
        holder.dashBoardLocation.setText(shopVisitModel.getDashBoardLocation());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh-mm a");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        String currentTime = simpleTimeFormat.format(date);

        holder.tvTick_Date.setText(currentDate);
        holder.tvTick_Time.setText(currentTime);

        if (shopVisitModel.isPlaced()) {
            holder.mLinearLayout.setVisibility(View.VISIBLE);
        }else {
            holder.mLinearLayout.setVisibility(View.INVISIBLE);
        }

    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(ShopVisitModel visitModel,int position);
    }
    @Override
    public int getItemCount() {
        return shopVisitModelList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView dashBoardShopName, dashBoardLocation;
        public TextView tvTick_Date;
        public TextView tvTick_Time;

        protected LinearLayout mLinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            dashBoardShopName = (TextView) itemView.findViewById(R.id.dashBoardShopName);
            dashBoardLocation = (TextView) itemView.findViewById(R.id.dashBoardLocation);
            tvTick_Date = (TextView) itemView.findViewById(R.id.tvTick_Date);
            tvTick_Time = (TextView) itemView.findViewById(R.id.tvTick_Time);
            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.temp) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClicked(shopVisitModelList.get(getAdapterPosition()),getAdapterPosition());
                    }
                }
            });
        }
    }


}