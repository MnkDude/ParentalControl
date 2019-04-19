package com.nizam.training.parentalcontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BlockService extends Service {
    static final String TAG = "SR";
    final int startupID = 1111111;

    public BlockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final AlarmManager alarmManager = (AlarmManager) getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        try {
            Intent i7 = new Intent(getApplicationContext(), SearchRunningApp.class);
            PendingIntent ServiceManagementIntent;
            ServiceManagementIntent = PendingIntent.getBroadcast(getApplicationContext(), startupID, i7, 0);
            /*alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(),
                    5000, ServiceManagementIntent);*/

        } catch (Exception e) {
            Log.i(TAG, "Exception : " + e);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
