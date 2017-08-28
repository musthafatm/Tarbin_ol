package com.iroid.tarbinol.app_prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GlobalPreferManager {

    private static SharedPreferences preferences;

    public static void saveArray(String tag, ArrayList<String> mArray) {
        JSONArray array = new JSONArray(mArray);
        String json = array.toString();
        Editor editor = preferences.edit();
        editor.putString(tag, json);
        editor.apply();
    }

    public static ArrayList<String> loadArray(String tag) {
        ArrayList<String> array = new ArrayList<String>();


        String json = preferences.getString(tag, "");

        try {

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                array.add(jsonArray.getString(i));

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return array;
    }

    public static void initializePreferenceManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static void setString(String key, String value) {

        Log.e(key, value);

        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setFloat(String key, float value) {
        Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

}
