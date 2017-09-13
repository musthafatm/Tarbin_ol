package com.iroid.tarbinol.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
import com.iroid.tarbinol.adapters.StockTakingRecyclerAdapter;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.model.ProductDetails;
import com.iroid.tarbinol.model.ProductDisplayResponseModel;
import com.iroid.tarbinol.model.StockTakingModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.gson.reflect.TypeToken.get;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class StockTakingActivity extends AppCompatActivity implements View.OnClickListener {



    private Toolbar ntoolbar;

    private List<StockTakingModel> stockTakingModelList = new ArrayList<>();
    private RecyclerView mrecyclerView;
    private List<ProductDetails> productDetailsList = new ArrayList<>();

    private StockTakingRecyclerAdapter stockAdapter;
    private Button morderButton = null;
//    int i;

   /* String[] paint_name = {"Asian paints","Asian paints","Asian paints","Asian paints", "Asian paints","Asian paints","Asian paints","Asian paints","Asian paints"};
    String[] paint_describe = {"apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex","apex duracast pebbletex"};
    String[] litre_string = {"30kg/ltr", "10 ltr","10 ltr","10 ltr","10 ltr","10 ltr","10 ltr","10 ltr","10 ltr"};
    String[] required_string = {"12","00","00","00","00","00","00","00","00"};
    String[] inStock_string = {"23","12","12","12","12","12","12","12","12"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_taking);

        ntoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(ntoolbar);
        Bundle extras = getIntent().getExtras();
        String shopNameTitle = extras.getString("shop_name_title");
        String shopId = extras.getString("shop_id");
        getSupportActionBar().setTitle(shopNameTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecyclerView = (RecyclerView) findViewById(R.id.stockRecycler);

//        *****************
        callProductApi(shopId);

      /*  for(i=0; i<9; i++) {
            stockTakingModelList.add(new StockTakingModel(paint_name[i], paint_describe[i], litre_string[i], required_string[i], inStock_string[i]));
        }
*/
        stockAdapter = new StockTakingRecyclerAdapter(productDetailsList);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(stockAdapter);

        morderButton = (Button) findViewById(R.id.orderButton);
        morderButton.setOnClickListener(this);
    }

    private void callProductApi(String shopId) {
         WebService webService = App.getClient().create(WebService.class);
        Call<ProductDisplayResponseModel> call = webService.productDisplay(shopId);
        call.enqueue(new Callback<ProductDisplayResponseModel>() {
            @Override
            public void onResponse(Call<ProductDisplayResponseModel> call, Response<ProductDisplayResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {

                        productDetailsList.addAll(response.body().getData());

                        stockAdapter.notifyDataSetChanged();



//                        String description = response.body().getData().get(0).getDescription();
//
//                        Intent checkInIntent = new Intent(getActivity(), CheckinActivity.class);
//                        checkInIntent.putExtra("shop", visitModel.getShopname());
//                        checkInIntent.putExtra("days", visitModel.getDays());
//                        checkInIntent.putExtra("list_id", position);
//                        checkInIntent.putExtra("shop_id", visitModel.getShopId());
//                        checkInIntent.putExtra("description", description);

//                        startActivityForResult(checkInIntent, REQUEST_RESULT);
                    }else {
                        showToast(getApplicationContext(), "Response Failure");
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductDisplayResponseModel> call, Throwable t) {
                showToast(getApplicationContext(), "NO_INTERNET_ACCESS");
            }
        });
    }



    // Up-Enabled action given by following code
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {


        //call editproduct api here


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null, false);
        builder.setView(view);
        Button button = (Button) view.findViewById(R.id.button);
        final AlertDialog alertDialog = builder.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
       alertDialog.show();
    }

}
