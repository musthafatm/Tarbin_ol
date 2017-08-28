package com.iroid.tarbinol.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by VIDHU on 12/21/2016.
 */

public class CommonUtils {

    public static final String MOBILE_PATTERN = "[0-9]{10}";
    public static final String PIN_CODE_PATTERN = "[0-9]{6}";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private static SimpleDateFormat beforeFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);


    public static void showSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        return beforeFormatOne.format(c.getTime());
    }


    public static boolean isValidMobile(String phone) {
        if (phone.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }


    public static String saveToSd(Bitmap bm, String filename) {

        if (bm != null) {
            File ishtaaDir = new File(Environment.getExternalStorageDirectory() + "/Ishtaa/");
            if (!ishtaaDir.exists())
                ishtaaDir.mkdirs();
            File dest = new File(ishtaaDir, filename);
            try {
                FileOutputStream out = new FileOutputStream(dest);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String path = dest.getAbsolutePath();
            Log.d("path ", path);
            return path;
        }

        return null;
    }

}
