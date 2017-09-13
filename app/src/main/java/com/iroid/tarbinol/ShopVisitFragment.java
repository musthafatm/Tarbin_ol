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
    public String shop_id;
    private String desc;
//    public static int shopId;

   /* private String fragCity;
    private String fragPlace;
    private String fragShopName;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop_visit, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        Bundle arguments = getArguments();
        String day = arguments.getString("DAY");

        callApi(day);
//        callCheckInApi(day);


        mAdapter = new ShopVisitRecyclerAdapter(shopVisitModelList);

        //****************
//        ShopDetails visitModelLocal = new ShopDetails();
//        shop_id = visitModelLocal.getShopId();
                //***************


        mAdapter.setOnItemClickListener(new ShopVisitRecyclerAdapter.OnItemClickListener() {

            //******-----------------**************
            @Override
            public void onItemClicked(ShopDetails visitModel, int position) {
                Toast.makeText(getActivity(), visitModel.getShopId(), Toast.LENGTH_SHORT).show();

//
//                CheckInDetails checkInDetailsModel = checkInDetailsList.get(position);
//                desc =  checkInDetailsModel.getDescription();




                Intent checkInIntent = new Intent(getActivity(), CheckinActivity.class);
                checkInIntent.putExtra("shop", visitModel.getShopname());
                checkInIntent.putExtra("days", visitModel.getDays());
                checkInIntent.putExtra("list_id", position);
                checkInIntent.putExtra("shop_id", visitModel.getShopId());
//                checkInIntent.putExtra("desc", desc);

                startActivityForResult(checkInIntent, REQUEST_RESULT);
            }
        });
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }


    //**********************************************************************************

    private void callCheckInApi(String day) {
        WebService webService = App.getClient().create(WebService.class);
        Call<JsonObject> call = webService.check_in_Task(AppPreferences.getStringData(getActivity(),
                AppPreferences.EMP_ID),day,shop_id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.body().getStatus().equalsIgnoreCase("success")) {
                if (response.isSuccessful()) {
                    JsonObject jsonObj = response.body();
                    String status = jsonObj.get("status").getAsString();
                    if (status.equals("success")) {

                    desc = jsonObj.get("description").getAsString();
                        showToast(getActivity(), desc);


//                    checkInDetailsList.addAll(response.body().getData());
//
//                    CheckInDetails checkInDetailsModel = checkInDetailsList.get(0);
//                    desc =  checkInDetailsModel.getDescription();


//                    mAdapter.notifyDataSetChanged();
                    } else {
                        showToast(getActivity(), "Response Failure");
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                showToast(getActivity(),"NO_INTERNET_ACCESS");
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

                    shopVisitModelList.addAll(response.body().getData());

                    mAdapter.notifyDataSetChanged();

                } else {
                    showToast(getActivity(),"Response Failure");
                }

            }

            @Override
            public void onFailure(Call<ShopVisitResponseModel> call, Throwable t) {

               showToast(getActivity(),"NO_NETWORK_ACCESS");
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

                    shopVisitModel.setPlaced(true);

                    mAdapter.notifyDataSetChanged();


                    break;
            }
        }
    }







    /* // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    *//**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     *//*
    public ShopVisitFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShopVisitFragment newInstance(int columnCount) {
        ShopVisitFragment fragment = new ShopVisitFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_visit_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ShopVisitListRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
*/

}
