package com.iroid.tarbinol.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.model.DayItem;
import com.iroid.tarbinol.model.ShopVisitModel;
import com.iroid.tarbinol.ui.DashboardActivity;
import com.iroid.tarbinol.ui.ExecutiveNameActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Acer on 25-Aug-17.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    private List<DayItem> dayItemList;
    private OnItemClickListener onItemClickListener;

    public DayAdapter(List<DayItem> dayItemList){this.dayItemList = dayItemList;}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_day_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DayItem dayItem = dayItemList.get(position);
        holder.mTvMonday.setText(dayItem.getDays());
        holder.mMondayLocal.setText(dayItem.getLocation());
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(DayItem dayItem, int position);
    }
    @Override
    public int getItemCount() {
        return dayItemList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvMonday, mMondayLocal;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvMonday = (TextView) itemView.findViewById(R.id.tvMonday);
            mMondayLocal = (TextView) itemView.findViewById(R.id.mondayLocal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClicked(dayItemList.get(getAdapterPosition()),getAdapterPosition());

                    }
                }
            });

        }
    }




}
