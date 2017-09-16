package com.iroid.tarbinol.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
import com.iroid.tarbinol.ShopVisitFragment;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.app_prefs.AppPreferences;
import com.iroid.tarbinol.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iroid.tarbinol.ui.LoginActivity.call;
import static com.iroid.tarbinol.ui.LoginActivity.webService;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class CheckinActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CANCEL = 121;
    private TextView mtvCheckInShopName;
    private TextView mtvCheckInShopDescription;
    private TextView mtvCheckInDate;
    private TextView mtvCheckInTime;
    private RelativeLayout mcheckInRelative;

    private Toolbar mToolbar;

    public String shop;
    public String shopId;
    public String days;
    private String description;


    LocationManager mlocManager;
    Location location = null;
    public double locationLat;
    public double locationLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        mtvCheckInShopName = (TextView) findViewById(R.id.tvCheckInShopName);
        mtvCheckInShopDescription = (TextView) findViewById(R.id.tvCheckInShopDescription);
        mtvCheckInDate = (TextView) findViewById(R.id.tvcheckInDate);
        mtvCheckInTime = (TextView) findViewById(R.id.tvcheckInTime);
        mcheckInRelative = (RelativeLayout) findViewById(R.id.checkInRelative);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy EEEE");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh-mm a");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        String currentTime = simpleTimeFormat.format(date);


        Bundle extras = getIntent().getExtras();
        shop = extras.getString("shop");
        shopId = extras.getString("shop_id");
        days = extras.getString("days");
        description = extras.getString("description");

        mtvCheckInShopName.setText(shop);
        mtvCheckInShopDescription.setText(description);
        mtvCheckInDate.setText(currentDate);
        mtvCheckInTime.setText(currentTime);


        mcheckInRelative.setOnClickListener(this);

        //***************---------------*************-------------************

        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

    }

    public class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            location = loc;
        }

        public void onProviderDisabled(String provider) {
            //nothin
        }

        public void onProviderEnabled(String provider) {
            //nothin
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            //nothin
        }
    }/* End of Class MyLocationListener */

//********************************----------------------------------////////////******************


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

        if(location!=null){
            locationLat = location.getLatitude();
            locationLong = location.getLongitude();
            showToast(getApplicationContext(), "lat: " + locationLat + ", long: " + locationLong);
            callCheckInTimeApi(AppPreferences.getStringData(getApplicationContext(),
                    AppPreferences.EMP_ID), shopId, locationLat, locationLong);
        }else{
            showToast(getApplicationContext(), "Waiting for location..");
        }



    }

    private void callCheckInTimeApi(String empId, String shop_Id, double locLat,double locLong) {
        webService = App.getClient().create(WebService.class);
        call = webService.checkin_TimeUpdate(empId,shop_Id,locLat,locLong);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    JsonObject jsonObj = response.body();
                    String status = jsonObj.get("status").getAsString();
                    if (status.equals("success")) {

                        Intent checkInIntent = new Intent(getApplicationContext(), StockTakingActivity.class);
                        int list_id = getIntent().getIntExtra("list_id", 0);

                        checkInIntent.putExtra("list_id", list_id);
                        checkInIntent.putExtra("shop_name_title", mtvCheckInShopName.getText().toString());
                        checkInIntent.putExtra("shop_id", shopId);

                        startActivityForResult(checkInIntent, REQUEST_CANCEL);

                    } else {
                        String msg = jsonObj.get("message").getAsString();
                        CommonUtils.showToast(getApplicationContext(), msg);
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                showToast(CheckinActivity.this, "No_Network_ACCESS");
            }
        });
    }









    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CANCEL) {
            switch (resultCode) {
                case RESULT_OK:


                    setResult(RESULT_OK, data);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
