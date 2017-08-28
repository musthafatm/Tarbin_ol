package com.iroid.tarbinol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iroid.tarbinol.adapters.ShopVisitRecyclerAdapter;
import com.iroid.tarbinol.dummy.DummyContent;
import com.iroid.tarbinol.dummy.DummyContent.DummyItem;
import com.iroid.tarbinol.model.ShopVisitModel;
import com.iroid.tarbinol.ui.CheckinActivity;

import java.util.ArrayList;
import java.util.List;

public class ShopVisitFragment extends Fragment {

    private static final int REQUEST_RESULT = 3232;
    private List<ShopVisitModel> shopVisitModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShopVisitRecyclerAdapter mAdapter;
    int i;
    String[] shopName = {"Kerala Hardware Shop", "Jyothi Paint Shop", "Johnson Hardware Shop", "Indira Hardwares", "Matha Paint and Hardwares", "Peevees Hardwares", "Kareems Hardwares", "Mahatma Hardwares", "Aleena Hardwares and Paints"};

    String[] shopLocation = {"Palarivattom,cochin", "Thammanam,cochin", "Thammanam South,cochin", "Padivattom,cochin", "Vyttila,cochin", "Punnurunni,cochin","Kuthappady,cochin","Naroth Road,cochin","Bavarapparambu,cochin"};

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

        for (i=0;i<9;i++) {
            shopVisitModelList.add(new ShopVisitModel(shopName[i], shopLocation[i]));

        }

        mAdapter = new ShopVisitRecyclerAdapter(shopVisitModelList);
        mAdapter.setOnItemClickListener(new ShopVisitRecyclerAdapter.OnItemClickListener() {
    @Override
    public void onItemClicked(ShopVisitModel visitModel,int position) {
        Toast.makeText(getActivity(), visitModel.getDashBoardShopName(), Toast.LENGTH_SHORT).show();

        Intent checkInIntent = new Intent(getActivity(), CheckinActivity.class);
        checkInIntent.putExtra("shop",visitModel.getDashBoardShopName());
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
