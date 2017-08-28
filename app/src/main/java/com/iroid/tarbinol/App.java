package com.iroid.tarbinol;

import android.app.Application;

import com.iroid.tarbinol.app_prefs.GlobalPreferManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by VIDHU on 1/11/2017.
 */

public class App extends Application {

    public static final String BASE_URL = "http://54.201.67.32/rest/";

    static OkHttpClient.Builder httpClient = null;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        GlobalPreferManager.initializePreferenceManager(getApplicationContext());

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

    }

}
