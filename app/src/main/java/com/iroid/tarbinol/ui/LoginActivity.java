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

import com.google.gson.JsonObject;
import com.iroid.tarbinol.App;
import com.iroid.tarbinol.R;
import com.iroid.tarbinol.api.WebService;
import com.iroid.tarbinol.utils.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;
import static android.graphics.Color.YELLOW;
import static com.iroid.tarbinol.utils.CommonUtils.showToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText metUserId;
    private EditText metPassword;
    private Button mloginButton = null;


    Call<JsonObject> call ;
    WebService webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        metUserId = (EditText) findViewById(R.id.etUserId);
        metPassword = (EditText) findViewById(R.id.etPassword);
        mloginButton = (Button) findViewById(R.id.login_button);


        webService = App.getClient().create(WebService.class);
//        Call<JsonObject> call ;



        mloginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (metUserId.getText().toString() == "" || metPassword.getText().toString() == "") {
            showToast(getApplicationContext(), "Logged in successfully");
        } else {
            call = webService.login(metUserId.getText().toString(), metPassword.getText().toString());
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        showToast(getApplicationContext(), "Logged in successfully");
                        Intent intent = new Intent(getApplicationContext(), ExecutiveNameActivity.class);
                        startActivity(intent);
                    }
                }


                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                    Response<JsonObject> responses = null;
                    if (!(responses.isSuccessful())) {
                        showToast(getApplicationContext(), responses.message());

                    }
                }
            });


        }
    }
}

