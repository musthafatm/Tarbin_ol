package com.iroid.tarbinol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.iroid.tarbinol.adapters.ShopVisitRecyclerAdapter;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.app_prefs.AppPreferences;
import com.iroid.tarbinol.model.CheckInDetails;
import com.iroid.tarbinol.model.CheckinResponseModel;
import com.iroid.tarbinol.model.ShopDetails;
import com.iroid.tarbinol.model.ShopVisitResponseModel;
import com.iroid.tarbinol.ui.CheckinActivity;
import com.iroid.tarbinol.ui.DashboardActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class ShopVisitFragment extends Fragment {

    private static final int REQUEST_RESULT = 3232;
    private List<ShopDetails> shopVisitModelList = new ArrayList<>();
    private List<CheckInDetails> checkInDetailsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShopVisitRecyclerAdapter mAdapter;
    int i;

    public String emp_name;

    /*
     String[] shopName = {"Kerala Hardware Shop", "Jyothi Paint Shop", "Johnson Hardware Shop", "Indira Hardwares", "Matha Paint and Hardwares", "Peevees Hardwares", "Kareems Hardwares", "Mahatma Hardwares", "Aleena Hardwares and Paints"};
     String[] shopLocation = {"Palarivattom,cochin", "Thammanam,cochin", "Thammanam South,cochin", "Padivattom,cochin", "Vyttila,cochin", "Punnurunni,cochin", "Kuthappady,cochin", "Naroth Road,cochin", "Bavarapparambu,cochin"};
 */
    public ShopVisitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_visit, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        Bundle arguments = getArguments();
        String day = arguments.getString("DAY");

        callApi(day);
//        callCheckInApi(day);

        mAdapter = new ShopVisitRecyclerAdapter(shopVisitModelList, ShopVisitRecyclerAdapter.ITEM_STATE_VISITED);
        mAdapter.setOnItemClickListener(new ShopVisitRecyclerAdapter.OnItemClickListener() {
            //******-----------------**************
            @Override
            public void onItemClicked(ShopDetails visitModel, int position) {
                Toast.makeText(getActivity(), "Already Checked in", Toast.LENGTH_SHORT).show();

                //COMMENT BELOW LINE AFTERALL..........
                callCheckInApi(visitModel,position);
            }


        });

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }


    //**********************************************************************************

    private void callCheckInApi(final ShopDetails visitModel, final int position) {
        WebService webService = App.getClient().create(WebService.class);
        Call<CheckinResponseModel> call = webService.check_in_Task(AppPreferences.getStringData(getActivity(),
                AppPreferences.EMP_ID), visitModel.getDays(), visitModel.getShopId());
        call.enqueue(new Callback<CheckinResponseModel>() {
            @Override
            public void onResponse(Call<CheckinResponseModel> call, Response<CheckinResponseModel> response) {
//                if (response.body().getStatus().equalsIgnoreCase("success")) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        String description = response.body().getData().get(0).getDescription();

                        Intent checkInIntent = new Intent(getActivity(), CheckinActivity.class);
                        checkInIntent.putExtra("shop", visitModel.getShopname());
                        checkInIntent.putExtra("days", visitModel.getDays());
                        checkInIntent.putExtra("list_id", position);
                        checkInIntent.putExtra("shop_id", visitModel.getShopId());
                        checkInIntent.putExtra("description", description);

                        startActivityForResult(checkInIntent, REQUEST_RESULT);
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckinResponseModel> call, Throwable t) {
                showToast(getActivity(), "NO_INTERNET_ACCESS");
            }
        });
    }

    //*************************************************************************************


    private void callApi(String day) {

        WebService webService = App.getClient().create(WebService.class);

        Call<ShopVisitResponseModel> call = webService.todayTask(AppPreferences.getStringData(getActivity(),
                AppPreferences.EMP_ID), day);

        call.enqueue(new Callback<ShopVisitResponseModel>() {
            @Override
            public void onResponse(Call<ShopVisitResponseModel> call, Response<ShopVisitResponseModel> response) {

                if (response.body().getStatus().equalsIgnoreCase("success")) {

                    // Storing employee name from here ; to use it in ExecutiveNameActivity
                    emp_name = response.body().getData().get(0).getName();
                    AppPreferences.insertStringData(getActivity(), AppPreferences.EMP_NAME, emp_name);
                    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

//
                    shopVisitModelList.addAll(response.body().getData());

                    mAdapter.notifyDataSetChanged();


                } else {
                    showToast(getActivity(), "Response Failure");
                }

            }

            @Override
            public void onFailure(Call<ShopVisitResponseModel> call, Throwable t) {

                showToast(getActivity(), "NO_NETWORK_ACCESS");
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_RESULT) {
            switch (resultCode) {
                case AppCompatActivity.RESULT_OK:

                    Bundle extras = data.getExtras();
                    int list_id = extras.getInt("list_id");


                    ShopDetails shopVisitModel = shopVisitModelList.get(list_id);


                    String hider = shopVisitModel.getStatus();

                    showToast(getActivity(), hider);

//                    if (hider == "1") {
//                    }

//                    shopVisitModel.setPlaced(true);

                    mAdapter.notifyDataSetChanged();


                    break;
            }
        }
    }


}
