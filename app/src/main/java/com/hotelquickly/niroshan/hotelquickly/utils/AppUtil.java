package com.hotelquickly.niroshan.hotelquickly.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.hotelquickly.niroshan.hotelquickly.beans.BeanObjectList;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * com.hotelquickly.niroshan.hotelquickly.utils
 * <p/>
 * Created by Niroshan Rathnayake.
 */

public class AppUtil {

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int height;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            height = display.getHeight(); // deprecated
        } else {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }

        return height;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            width = display.getWidth(); // deprecated

        } else {
            Point size = new Point();
            display.getSize(size);
            width = size.x;

        }

        return width;
    }

    public static AlertDialog showAlert(Context context, String title,
                                        String message, DialogInterface.OnClickListener listener) {
        if (listener == null) {
            listener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };
        }

        AlertDialog d = new AlertDialog.Builder(context).setMessage(message)
                .setTitle(title).setCancelable(true)
                .setNeutralButton(android.R.string.ok, listener).show();
        return d;
    }

    /**
     * Check the network connection of the device.
     *
     * @param context current context value
     * @return true if network connection available false otherwise
     */
    public static boolean checkNetworkConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworks = connectivityManager.getActiveNetworkInfo();
        if (activeNetworks != null && activeNetworks.isConnected()) {
            return activeNetworks.isConnectedOrConnecting();
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }
            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }

    private static String replaceValues(String url) throws IOException {

        String URL = url;

        String userId = "276";
        String appSecretKey = "gvx32RFZLNGhmzYrfDCkb9jypTPa8Q";
        String currencyCode = "USD";
        String offerId = "10736598";
        String selectedVouchers = "[]";

        if(URL.contains("{userId}")){
            URL = URL.replace("{userId}", userId);
        }
        if(URL.contains("{appSecretKey}")){
            URL = URL.replace("{appSecretKey}", appSecretKey);
        }
        if(URL.contains("{currencyCode}")){
            URL = URL.replace("{currencyCode}", currencyCode);
        }
        if(URL.contains("{offerId}")){
            URL = URL.replace("{offerId}", offerId);
        }
        if(URL.contains("{selectedVouchers}")){
            URL = URL.replace("{selectedVouchers}", selectedVouchers);
        }
        return URL;
    }


    public static ArrayList<BeanObjectList> parseResult(String result) throws IOException {

        ArrayList<BeanObjectList> fetchedList = new ArrayList<>();
        String url = "";

        try{
            JSONObject response = new JSONObject(result);

            Iterator iterator = response.keys();

            while(iterator.hasNext()){
                String key = (String)iterator.next();
                JSONObject issue = response.getJSONObject(key);

                if(!issue.isNull("url")){
                    url = replaceValues(issue.getString("url"));
                }

                if(!issue.isNull("pageTitle")){

                    BeanObjectList item = new BeanObjectList(issue.getString("cache"), issue.getString("pageTitle"), url);
                    fetchedList.add(item);
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return fetchedList;
    }


}
