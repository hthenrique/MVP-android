package com.example.mvpandroid.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!NetworkUtil.getConnectivityStatus(context)){
            Intent noConnection = new Intent(context, SemConexao.class);
            noConnection.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            noConnection.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(noConnection);
        }
    }
}
