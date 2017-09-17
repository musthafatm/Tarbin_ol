package com.iroid.tarbinol.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.model.ShopDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 25-Aug-17.
 */

public class ShopVisitRecyclerAdapter extends RecyclerView.Adapter<ShopVisitRecyclerAdapter.MyViewHolder> {

    public static final int ITEM_STATE_PENDING = 1;
    public static final int ITEM_STATE_VISITED = 2;
    private  int state;
    private  String srvr_Date;
    private  String srvr_Time;

    private List<ShopDetails> shopVisitModelList;
    private OnItemClickListener onItemClickListener;

    public ShopVisitRecyclerAdapter(List<ShopDetails> shopVisitModelList,int state, String srvr_Date,String srvr_Time) {
        this.shopVisitModelList = shopVisitModelList;
        this.state = state;
        this.srvr_Date = srvr_Date;
        this.srvr_Time = srvr_Time;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_visit_row_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ShopDetails shopVisitModel = shopVisitModelList.get(position);
        holder.dashBoardShopName.setText(shopVisitModel.getShopname());
        holder.dashBoardLocation.setText(shopVisitModel.getCity() + ", " + shopVisitModel.getPlace());

        //DATE  >>>>>>>>>>>>EDITING

        if(srvr_Date == "date Name" && srvr_Time == "time Name") {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh-mm a");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        String currentTime = simpleTimeFormat.format(date);
            holder.tvTick_Date.setText(currentDate);
            holder.tvTick_Time.setText(currentTime);

        }else {
            holder.tvTick_Date.setText(srvr_Date);
            holder.tvTick_Time.setText(srvr_Time);
        }
        //DATE  >>>>>>>>>>>>EDITING


        //****************/////////////////***************
        if (state == ITEM_STATE_VISITED && shopVisitModel.getStatus().equalsIgnoreCase("1") ) {
            holder.mLinearLayout.setVisibility(View.VISIBLE);
        } else {
            holder.mLinearLayout.setVisibility(View.INVISIBLE);
        }

    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(ShopDetails visitModel, int position);
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
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.temp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(shopVisitModelList.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });
        }
    }


}
