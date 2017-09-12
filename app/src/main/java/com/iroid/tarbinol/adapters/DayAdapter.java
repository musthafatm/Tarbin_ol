package com.iroid.tarbinol.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.model.DayItem;
import com.iroid.tarbinol.model.ShopVisitModel;
import com.iroid.tarbinol.ui.DashboardActivity;
import com.iroid.tarbinol.ui.ExecutiveNameActivity;
import com.iroid.tarbinol.ui.LoginActivity;
import com.iroid.tarbinol.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iroid.tarbinol.utils.CommonUtils.showToast;
import static java.security.AccessController.getContext;

/**
 * Created by Acer on 25-Aug-17.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    private List<DayItem> dayItemList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public static WebService webService;
    public static Call<JsonObject> call;

    String shopname;
    String city;
    String place;

    public DayAdapter(List<DayItem> dayItemList, Context context){
        this.dayItemList = dayItemList;
        this.mContext = context;
    }


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

            webService = App.getClient().create(WebService.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String day = mTvMonday.getText().toString();

                    call = webService.todayTask(day);
                    call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {

                    JsonObject jsonObj = response.body();
                    String status = jsonObj.get("status").getAsString();
                    if (status.equals("success")) {

                        JsonArray jsonArray = jsonObj.get("data").getAsJsonArray();

                            for (int i = 0; i < jsonArray.size(); i++) {
                                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                                shopname = jsonObject.get("shopname").getAsString();
                                city = jsonObject.get("city").getAsString();
                                place = jsonObject.get("place").getAsString();
                            }





                   Context c = mContext;
                    Intent intent = new Intent(c,DashboardActivity.class);
                    intent.putExtra("DAY", mTvMonday.getText().toString());
                    intent.putExtra("LOCATION", mMondayLocal.getText().toString());
                    intent.putExtra("City", city);
                    intent.putExtra("Place", place);
                    intent.putExtra("ShopName", shopname);
                    c.startActivity(intent);

                    } else {
                        String msg = jsonObj.get("message").getAsString();
                        showToast(mContext, msg);
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
//          ON ERROR COMMENT THE BELOW CODES
//                Response<JsonObject> responses = null;
//                if (!(responses.isSuccessful())) {             responses.message()
//                    showToast(v.getContext(), "No_Network_ACCESS");
//
//                }
            }
        });




                    Context c = v.getContext();
                    Intent intent = new Intent(c,DashboardActivity.class);
                    intent.putExtra("DAY", mTvMonday.getText().toString());
                    intent.putExtra("LOCATION", mMondayLocal.getText().toString());
                    c.startActivity(intent);


//                    if (onItemClickListener!=null) {
//                        onItemClickListener.onItemClicked(dayItemList.get(getAdapterPosition()),getAdapterPosition());

//                    }
                }
            });

        }
    }




}
