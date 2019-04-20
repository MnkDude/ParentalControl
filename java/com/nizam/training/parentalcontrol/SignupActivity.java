// SignupActivity.java
package com.nizam.training.parentalcontrol;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle(R.string.signup);
        Button btnSignup;
        WifiReceiver wifiReceiver = new WifiReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        new MyDB(this);
        final EditText pin = findViewById(R.id.pin);
        final EditText ConfirmPin = findViewById(R.id.ConPin);
        btnSignup = findViewById(R.id.btnSignup);
        alertDi(getString(R.string.alerttitle), getString(R.string.perminfo), Settings.ACTION_ACCESSIBILITY_SETTINGS);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sf = getSharedPreferences("PinNumber", 0);
                SharedPreferences.Editor editor = sf.edit();
                try {
                    editor.putInt("Pin", Integer.parseInt(pin.getText().toString()));
                    editor.putInt("ConfirmPin", Integer.parseInt(ConfirmPin.getText().toString()));
                    editor.apply();
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.pls), Toast.LENGTH_SHORT).show();
                }
                int num = sf.getInt("Pin", 0);
                int num2 = sf.getInt("ConfirmPin", 0);
                if (num == num2 && (num / 1000) >= 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.pinsuc), Toast.LENGTH_SHORT).show();
                    // finish();
                    SharedPreferences home = getSharedPreferences("home", MODE_PRIVATE);
                    startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
                    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    home.edit().putString("home", am.getRunningTasks(1).get(0).topActivity.getPackageName()).apply();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.pinwrg), Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            JobScheduler mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            ComponentName serviceName = new ComponentName(getPackageName(),
                    MyJobService.class.getName());
            JobInfo.Builder builder = new JobInfo.Builder(0, serviceName)
                    .setPeriodic(5000);
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);
        }
        else startService(new Intent(getApplicationContext(), BlockService.class));*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startActivity(new Intent(getApplicationContext(),SplashActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void alertDi(String Title, String msg, final String action) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(Title);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(action);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
