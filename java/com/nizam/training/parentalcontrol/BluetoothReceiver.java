package com.nizam.training.parentalcontrol;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        //throw new UnsupportedOperationException("Not yet implemented");
        BluetoothAdapter bluetootbhAdapter = BluetoothAdapter.getDefaultAdapter();
        String s = intent.getAction();
        Log.i("bluemnk", "receiver started");
        if (s.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            if (bluetootbhAdapter.isEnabled()) {
                Log.i("bluemnk", "if executed");
                bluetootbhAdapter.disable();
            }
        }
    }
}