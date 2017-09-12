package com.iroid.tarbinol.ui;

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

import com.iroid.tarbinol.R;
import com.iroid.tarbinol.adapters.ShopVisitRecyclerAdapter;
import com.iroid.tarbinol.model.ShopDetails;

import java.util.ArrayList;
import java.util.List;

public class PendingTaskFragment extends Fragment {

    //HERE CODE 

    private static final int REQUEST_RESULT = 3232;
    private List<ShopDetails> shopVisitModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShopVisitRecyclerAdapter mAdapter;

    String[] shopName = {"Kerala Hardware Shop", "Jyothi Paint Shop", "Johnson Hardware Shop", "Indira Hardwares", "Matha Paint and Hardwares", "Peevees Hardwares", "Kareems Hardwares", "Mahatma Hardwares", "Aleena Hardwares and Paints"};

    String[] shopLocation = {"Palarivattom,cochin", "Thammanam,cochin", "Thammanam South,cochin", "Padivattom,cochin", "Vyttila,cochin", "Punnurunni,cochin","Kuthappady,cochin","Naroth Road,cochin","Bavarapparambu,cochin"};

    public PendingTaskFragment() {
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



        mAdapter = new ShopVisitRecyclerAdapter(shopVisitModelList);
        mAdapter.setOnItemClickListener(new ShopVisitRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(ShopDetails visitModel, int position) {
                Toast.makeText(getActivity(), visitModel.getShopname(), Toast.LENGTH_SHORT).show();

                Intent checkInIntent = new Intent(getActivity(), CheckinActivity.class);
                checkInIntent.putExtra("shop",visitModel.getShopname());
                checkInIntent.putExtra("list_id",position);
                startActivityForResult(checkInIntent, REQUEST_RESULT);
            }
        });
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_RESULT) {
            switch (resultCode) {
                case AppCompatActivity.RESULT_OK:
                    Bundle extras = data.getExtras();
                    int list_id = extras.getInt("list_id");

                    if (shopVisitModelList!=null&& shopVisitModelList.size()>0) {
//                        ShopVisitModel shopVisitModel = shopVisitModelList.get(list_id);

//                        shopVisitModel.setPlaced(true);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}