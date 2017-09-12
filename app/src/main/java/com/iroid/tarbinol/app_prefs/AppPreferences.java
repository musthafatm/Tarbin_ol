package com.iroid.tarbinol.app_prefs;/**
 * Created by Muhammed on 12/09/17.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    public static final String PREFERENCE_NAME = "Tarbin_ol";
    public static final String EMP_ID = "emp_id";

    /**
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void insertStringData(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static void insertBooleanData(Context context, String key, boolean value) {
        getSharedPreference(context).edit().putBoolean(key, value).apply();
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static String getStringData(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanData(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }
}
