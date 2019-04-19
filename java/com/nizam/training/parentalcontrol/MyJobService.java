package com.nizam.training.parentalcontrol;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    public MyJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.i("MNk", "Job Started");
        Intent intent = new Intent(getApplicationContext(), SearchRunningApp.class);
        sendBroadcast(intent);
    /*
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> alltasks = am
                .getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo alltask : alltasks) {
            Log.i("MNK",alltask.topActivity.toString());
            Log.i("MNK",alltask.baseActivity.toString());
            *//*Intent intent1=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:9003058570"));
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
*//*
            if (alltask.topActivity.toString().contains("com.android.phone"))
            {
                Intent intent=new Intent(getApplicationContext(), BlockActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }*/
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
