package com.example.mvpandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class NetworkUtil {
    public static String getConnectivityStatusString(Context context){
        Intent intent = new Intent(context, SemConexao.class);
        context.startActivity(intent);

        String status = null;
        ConnectivityManager cm = (ConnectivityManager)           context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled";
                return status;
            }
        } else {
            status = "No internet is available";
            return status;
        }
        return status;
    }

    public void activityConnection(Context context){
        Intent intent = new Intent(context, SemConexao.class);
        context.startActivity(intent);
    }

}
