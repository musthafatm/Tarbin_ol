package com.iroid.tarbinol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.iroid.tarbinol.adapters.DayAdapter;
import com.iroid.tarbinol.app_prefs.AppPreferences;
import com.iroid.tarbinol.model.DayItem;
import com.iroid.tarbinol.ui.DashboardActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iroid.tarbinol.ui.LoginActivity.call;
import static com.iroid.tarbinol.ui.LoginActivity.empId;
import static com.iroid.tarbinol.ui.LoginActivity.webService;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

/**
 * Created by Acer on 24-Aug-17.
 */

public class ContentFragment extends Fragment implements View.OnClickListener{

    RecyclerView rvDay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_executive_name, container, false);

        rvDay = (RecyclerView) view.findViewById(R.id.rvDay);
        rvDay.setLayoutManager(new LinearLayoutManager(getActivity()));

        call = webService.chooseDay(AppPreferences.getStringData(getActivity(), AppPreferences.EMP_ID));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    JsonObject jsonObj = response.body();
                    String status = jsonObj.get("status").getAsString();
                    if(status.equals("success")) {

                        List<DayItem> dayItems = new ArrayList<DayItem>();
                        JsonArray jsonArray = jsonObj.get("data").getAsJsonArray();
                       for (int i = 0; i < jsonArray.size(); i++){

                           JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

//                           dayItems.add(new DayItem(jsonObject.get("days").getAsString(), jsonObject.get("location").getAsString()));

//                           Gson gson = new Gson();
//                           Type type = new TypeToken<List<DayItem>>() {}.getType();
//                           String json = gson.toJson(dayItems, type);
//                           System.out.println(json);
//                           List<DayItem> fromJson = gson.fromJson(json, type);
//                           for (DayItem day_Item : fromJson) {
//                               System.out.println(day_Item);
//                           }

                           dayItems.add(new DayItem(jsonObject.get("day").getAsString(),
                                   jsonObject.get("location").getAsString()));


                       }


                        DayAdapter dayAdapter = new DayAdapter(dayItems, getActivity());
                        rvDay.setAdapter(dayAdapter);


                    }else{
                        String msg = jsonObj.get("data").getAsString();
                        showToast(getActivity(), msg);
                    }
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                showToast(getActivity(), "No_Network_ACCESS");
            }
        });



        return view;
    }

    @Override
    public void onClick(View v) {


//        switch (v.getId()) {
//            case R.id.mondayLinearLayout:

             /*   String username = AppStorageFactory.getUsername(this);
                String password = AppStorageFactory.getPassword(this);
                gotoActivity();*/

//                Intent intent1 = new Intent(getContext(), DashboardActivity.class);
//                intent1.putExtra("DAY", mtvMonday.getText().toString());
//                intent1.putExtra("LOCATION", mMondayLocal.getText().toString());
//                startActivity(intent1);
//                break;
//
//            case R.id.tuesdayLinearLayout:
//                Intent intent2 = new Intent(getContext(), DashboardActivity.class);
//                intent2.putExtra("DAY", mtvTuesday.getText().toString());
//                intent2.putExtra("LOCATION", mTuesdayLocal.getText().toString());
//                startActivity(intent2);
//                break;
//
//            case R.id.wednesdayLinearLayout:
//                Intent intent3 = new Intent(getContext(), DashboardActivity.class);
//                intent3.putExtra("DAY", mtvWednesday.getText().toString());
//                intent3.putExtra("LOCATION", mWednesdayLocal.getText().toString());
//                startActivity(intent3);
//                break;
//
//            case R.id.thursdayLinearLayout:
//                Intent intent4 = new Intent(getContext(), DashboardActivity.class);
//                intent4.putExtra("DAY", mtvThursday.getText().toString());
//                intent4.putExtra("LOCATION", mThursdayLocal.getText().toString());
//                startActivity(intent4);
//                break;
//
//            case R.id.fridayLinearLayout:
//                Intent intent5 = new Intent(getContext(), DashboardActivity.class);
//                intent5.putExtra("DAY", mtvFriday.getText().toString());
//                intent5.putExtra("LOCATION", mFridayLocal.getText().toString());
//                startActivity(intent5);
//                break;
//
//            case R.id.saturdayLinearLayout:
//                Intent intent6 = new Intent(getContext(), DashboardActivity.class);
//                intent6.putExtra("DAY", mtvSaturday.getText().toString());
//                intent6.putExtra("LOCATION", mSaturdayLocal.getText().toString());
//                startActivity(intent6);
//                break;
//
//            case R.id.otherLinearLayout:
//                Intent intent7 = new Intent(getContext(), DashboardActivity.class);
//                intent7.putExtra("DAY", "Other");
//                startActivity(intent7);
//                break;
        }
}

