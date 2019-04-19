package com.nizam.training.parentalcontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SearchRunningApp extends BroadcastReceiver {

    private static final float incTime = 0.083333336f;
    final String TAG = "MNK";
    MyDB myDB;
    ArrayList<String> strings;
    ArrayList<Integer> isPerm;
    Context ct;

    @Override
    public void onReceive(Context aContext, Intent anIntent) {
        ct = aContext;
     /*   try {
            myDB = new MyDB(aContext);
            ct=aContext;
            ActivityManager am = (ActivityManager) aContext
                    .getSystemService(Context.ACTIVITY_SERVICE);
            strings = myDB.getPack();
            isPerm = myDB.getIsPermenant();
            SharedPreferences sharedPreferences = aContext.getSharedPreferences("BlockAppTime", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int i = 0;
            String s;
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                s=retrieveStats();
            else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                s=am.getRunningAppProcesses().get(0).processName;
            else s=am.getRunningTasks(1).get(0).topActivity.flattenToShortString();
                for (String pack_name : strings) {
                    if (s.contains(pack_name)) {
                        int HoursOfDay = myDB.getData(AppSettingActivity.HoursOfDay, pack_name);
                        int MinutesOfHours = myDB.getData(AppSettingActivity.MinutesOfDay, pack_name);
                        int timer = HoursOfDay * 60 + MinutesOfHours;
                        int startHour = myDB.getData(AppSettingActivity.StartTimeInHours, pack_name);
                        int startMinute = myDB.getData(AppSettingActivity.StartTimeInMinutes, pack_name);
                        int endHour = myDB.getData(AppSettingActivity.EndTimeInHours, pack_name);
                        int endMinute = myDB.getData(AppSettingActivity.EndTimeInMinutes, pack_name);
                        int startTime = startHour * 60 + startMinute;
                        int endTime = endHour * 60 + endMinute;
                        int intweek1[]=AppSettingActivity.strToInt(myDB.getWeekData(AppSettingActivity.NoOfdayInWeek1,pack_name));
                        for (int i1 : intweek1) {
                            Log.i("weekvalue",i1+"");
                        }
                        int intweek2[]=AppSettingActivity.strToInt(myDB.getWeekData(AppSettingActivity.NoOfdayInWeek2,pack_name));
                        Calendar calendar = Calendar.getInstance();
                        int currentTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
                        if (isPerm.get(i) == 1) {
                            blockApp();
                        } else if (timer != 0) {
                            for (int i1 : intweek1) {
                                if(i1==calendar.get(Calendar.DAY_OF_WEEK)) {
                                    Log.i(TAG, "timer" + sharedPreferences.getFloat(pack_name, 0));
                                    editor.putFloat(pack_name, sharedPreferences.getFloat(pack_name, 0) + incTime).apply();
                                    if (timer <= sharedPreferences.getFloat(pack_name, 0)) {
                                        blockApp();
                                    }
                                }
                            }
                        } else if (!(startTime == 0 && endTime == 0)) {
                            for (int i1 : intweek2) {
                                if(i1==calendar.get(Calendar.DAY_OF_WEEK)) {
                                    Log.i(TAG, "inside if no1");
                                    if (startTime <= currentTime && endTime >= currentTime) {
                                        Log.i(TAG, "inside if no3");
                                        blockApp();
                                        Log.i(TAG, "Blocked app by stsst" + pack_name);
                                    }
                                }
                            }
                        }
                    }

                    i++;
                }
                Log.i(TAG, "aTask.topActivity: "+ s);
        } catch (Throwable t) {
            Log.i(TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String retrieveStats() {
        UsageStatsManager usm = (UsageStatsManager) ct.getSystemService(Context.USAGE_STATS_SERVICE);
        String currentApp="";
        long time = System.currentTimeMillis();
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
        if (appList != null && appList.size() > 0) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<>();
            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (!mySortedMap.isEmpty()) {
                try {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i("MnkFinal",currentApp);
        return currentApp;
    }*/
        blockApp();
    }

    private void blockApp() {
        Intent intent = new Intent(ct.getApplicationContext(), BlockActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        ct.startActivity(intent);
    }
}