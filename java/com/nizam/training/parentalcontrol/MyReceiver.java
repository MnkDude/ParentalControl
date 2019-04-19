package com.nizam.training.parentalcontrol;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    static final String TAG = "SR";
    final int startupID = 1111111;
    String pack;

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        final AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        try {
            Intent i7 = new Intent(context, SearchRunningApp.class);
            PendingIntent ServiceManagementIntent;
            ServiceManagementIntent = PendingIntent.getBroadcast(context, startupID, i7, 0);
           /* alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(),
                    5000, ServiceManagementIntent);*/
        } catch (Exception e) {
        }
    }
}