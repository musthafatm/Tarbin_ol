package com.iroid.tarbinol.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.iroid.tarbinol.R;
//import com.iroid.tarbinol.ShopVisitFragment;
import com.iroid.tarbinol.ShopVisitFragment;
import com.iroid.tarbinol.adapters.ViewPagerAdapter;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        Bundle extras = getIntent().getExtras();
        String day = extras.getString("DAY");
        String location = extras.getString("LOCATION");


        getSupportActionBar().setTitle(day);
        getSupportActionBar().setSubtitle(location);

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
        adapter.addFragment(new ShopVisitFragment(), "Shop Visit");
        adapter.addFragment(new PendingTaskFragment(),"Pending Task");
        viewPager.setAdapter(adapter);
    }
}
