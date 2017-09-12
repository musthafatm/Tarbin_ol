package com.iroid.tarbinol.ui;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.app_prefs.AppPreferences;
import com.iroid.tarbinol.utils.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;
import static android.graphics.Color.YELLOW;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText metUserId;
    private EditText metPassword;
    private Button mloginButton = null;

    public static String empId;

    public static WebService webService;
    public static Call<JsonObject> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        metUserId = (EditText) findViewById(R.id.etUserId);
        metPassword = (EditText) findViewById(R.id.etPassword);
        mloginButton = (Button) findViewById(R.id.login_button);


        webService = App.getClient().create(WebService.class);



        mloginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        String s1 = metUserId.getText().toString();
        String s2 = metPassword.getText().toString();
        if (s1.equals("")) {
            metUserId.setError("Enter User Name");
        }
        if (s2.equals("")) {
            metPassword.setError("Enter Password");
        }

        call = webService.login(s1, s2);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    JsonObject jsonObj = response.body();
                    String status = jsonObj.get("status").getAsString();
                    if(status.equals("success")) {
                        empId = jsonObj.get("employee_id").getAsString();
                        showToast(LoginActivity.this, "Logged in successfully " + empId);
                        Intent intent = new Intent(LoginActivity.this, ExecutiveNameActivity.class);
                        AppPreferences.insertStringData(LoginActivity.this, AppPreferences.EMP_ID,empId);
                        startActivity(intent);
                    }else{
                        String msg = jsonObj.get("message").getAsString();
                        CommonUtils.showToast(getApplicationContext(), msg);
                    }
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
//          ON ERROR COMMENT THE BELOW CODES
//                Response<JsonObject> responses = null;
//                if (!(responses.isSuccessful())) {             responses.message()
                    showToast(LoginActivity.this, "No_Network_ACCESS");
//
//                }
            }
        });
    }

}

