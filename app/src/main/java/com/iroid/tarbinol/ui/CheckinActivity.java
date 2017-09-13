package com.iroid.tarbinol.ui;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iroid.tarbinol.ui.LoginActivity.webService;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class CheckinActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CANCEL = 121;
    private TextView mtvCheckInShopName;
    private TextView mtvCheckInShopDescription;
    //    private Button mcheckinButton = null;
    private TextView mtvCheckInDate;
    private TextView mtvCheckInTime;
    private RelativeLayout mcheckInRelative;

    private Toolbar mToolbar;

    public String shop;
    public String shopId;
    public String days;
    private String description;


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
//        description = extras.getString("desc");



        mtvCheckInShopName.setText(shop);
        mtvCheckInShopDescription.setText(description);

        mtvCheckInDate.setText(currentDate);
        mtvCheckInTime.setText(currentTime);



//----------------------------***************************---------------------------
        mcheckInRelative.setOnClickListener(this);
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

        Intent checkInIntent = new Intent(this, StockTakingActivity.class);

        int list_id = getIntent().getIntExtra("list_id", 0);

        checkInIntent.putExtra("list_id", list_id);
        checkInIntent.putExtra("shop_name_title", mtvCheckInShopName.getText().toString());

        startActivityForResult(checkInIntent, REQUEST_CANCEL);

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
