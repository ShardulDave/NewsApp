package com.example.android.homework.Utility;

import android.content.Context;
import android.net.ConnectivityManager;



public class InternetUtils {

    public static boolean checkInternetConnectedOrNot(Context activityContext) {
        ConnectivityManager cm = (ConnectivityManager) activityContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) return false;
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
