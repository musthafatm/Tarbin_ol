package com.iroid.tarbinol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iroid.tarbinol.ui.DashboardActivity;

/**
 * Created by Acer on 24-Aug-17.
 */

public class ContentFragment extends Fragment implements View.OnClickListener{


    public TextView mtvMonday,mtvTuesday,mtvWednesday,mtvThursday,mtvFriday,mtvSaturday;
    public TextView mMondayLocal,mTuesdayLocal,mWednesdayLocal,mThursdayLocal,mFridayLocal,mSaturdayLocal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_executive_name, container, false);

        mtvMonday = (TextView) view.findViewById(R.id.tvMonday);
        mtvTuesday = (TextView) view.findViewById(R.id.tvTuesday);
        mtvWednesday = (TextView) view.findViewById(R.id.tvWednesday);
        mtvThursday = (TextView) view.findViewById(R.id.tvThursday);
        mtvFriday = (TextView) view.findViewById(R.id.tvFriday);
        mtvSaturday = (TextView) view.findViewById(R.id.tvSaturday);

        mMondayLocal = (TextView) view.findViewById(R.id.mondayLocal);
        mTuesdayLocal = (TextView) view.findViewById(R.id.tuesdayLocal);
        mWednesdayLocal = (TextView) view.findViewById(R.id.wednesdayLocal);
        mThursdayLocal = (TextView) view.findViewById(R.id.thursdayLocal);
        mFridayLocal = (TextView) view.findViewById(R.id.fridayLocal);
        mSaturdayLocal = (TextView) view.findViewById(R.id.saturdayLocal);



        LinearLayout mLinearLayout1 = (LinearLayout) view.findViewById(R.id.mondayLinearLayout);
        LinearLayout mLinearLayout2 = (LinearLayout) view.findViewById(R.id.tuesdayLinearLayout);
        LinearLayout mLinearLayout3 = (LinearLayout) view.findViewById(R.id.wednesdayLinearLayout);
        LinearLayout mLinearLayout4 = (LinearLayout) view.findViewById(R.id.thursdayLinearLayout);
        LinearLayout mLinearLayout5 = (LinearLayout) view.findViewById(R.id.fridayLinearLayout);
        LinearLayout mLinearLayout6 = (LinearLayout) view.findViewById(R.id.saturdayLinearLayout);
        LinearLayout mLinearLayout7 = (LinearLayout) view.findViewById(R.id.otherLinearLayout);

        mLinearLayout1.setOnClickListener(this);
        mLinearLayout2.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout4.setOnClickListener(this);
        mLinearLayout5.setOnClickListener(this);
        mLinearLayout6.setOnClickListener(this);
        mLinearLayout7.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mondayLinearLayout:

             /*   String username = AppStorageFactory.getUsername(this);
                String password = AppStorageFactory.getPassword(this);
                gotoActivity();*/

                Intent intent1 = new Intent(getContext(), DashboardActivity.class);
                intent1.putExtra("DAY", mtvMonday.getText().toString());
                intent1.putExtra("LOCATION", mMondayLocal.getText().toString());
                startActivity(intent1);
                break;

            case R.id.tuesdayLinearLayout:
                Intent intent2 = new Intent(getContext(), DashboardActivity.class);
                intent2.putExtra("DAY", mtvTuesday.getText().toString());
                intent2.putExtra("LOCATION", mTuesdayLocal.getText().toString());
                startActivity(intent2);
                break;

            case R.id.wednesdayLinearLayout:
                Intent intent3 = new Intent(getContext(), DashboardActivity.class);
                intent3.putExtra("DAY", mtvWednesday.getText().toString());
                intent3.putExtra("LOCATION", mWednesdayLocal.getText().toString());
                startActivity(intent3);
                break;

            case R.id.thursdayLinearLayout:
                Intent intent4 = new Intent(getContext(), DashboardActivity.class);
                intent4.putExtra("DAY", mtvThursday.getText().toString());
                intent4.putExtra("LOCATION", mThursdayLocal.getText().toString());
                startActivity(intent4);
                break;

            case R.id.fridayLinearLayout:
                Intent intent5 = new Intent(getContext(), DashboardActivity.class);
                intent5.putExtra("DAY", mtvFriday.getText().toString());
                intent5.putExtra("LOCATION", mFridayLocal.getText().toString());
                startActivity(intent5);
                break;

            case R.id.saturdayLinearLayout:
                Intent intent6 = new Intent(getContext(), DashboardActivity.class);
                intent6.putExtra("DAY", mtvSaturday.getText().toString());
                intent6.putExtra("LOCATION", mSaturdayLocal.getText().toString());
                startActivity(intent6);
                break;

            case R.id.otherLinearLayout:
                Intent intent7 = new Intent(getContext(), DashboardActivity.class);
                intent7.putExtra("DAY", "Other");
                startActivity(intent7);
                break;
        }
    }

 /*   private void gotoActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }*/

}
