package com.iroid.tarbinol.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.JsonObject;
import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
//import com.iroid.tarbinol.ShopVisitFragment;
import com.iroid.tarbinol.ShopVisitFragment;
import com.iroid.tarbinol.adapters.ViewPagerAdapter;
import com.iroid.tarbinol.api.WebService;

import retrofit2.Call;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    public String city;
    public String place;
    public String ShopName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        Bundle extras = getIntent().getExtras();

        String day = extras.getString("DAY");
        String location = extras.getString("LOCATION");
        city = extras.getString("City");
        place = extras.getString("Place");
        ShopName = extras.getString("ShopName");

        String dayCamel = toCamelCase(day);
        String locationCamel = toCamelCase(location);
        getSupportActionBar().setTitle(dayCamel);
        getSupportActionBar().setSubtitle(locationCamel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mviewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mviewPager);

        mtabLayout = (TabLayout) findViewById(R.id.tabs);
        mtabLayout.setupWithViewPager(mviewPager);


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



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        ShopVisitFragment shopVisitFragment = new ShopVisitFragment();
        shopVisitFragment.setArguments(getIntent().getExtras());


        PendingTaskFragment pendingTaskFragment = new PendingTaskFragment();
        pendingTaskFragment.setArguments(getIntent().getExtras());


        adapter.addFragment(shopVisitFragment, "Shop Visit");
        adapter.addFragment(pendingTaskFragment,"Pending Task");
        viewPager.setAdapter(adapter);
    }




// Below function is to convert a string to camel case
    public static String toCamelCase(final String init) {
        if (init==null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }





}
