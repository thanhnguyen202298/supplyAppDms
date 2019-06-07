package com.example.sskey.dms.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("<<Broaddcast","start in broadcast");
        Intent it = new Intent(context, OrderService.class);
        PendingIntent peservcie = PendingIntent.getService(context, OrderService.ServiceID, it, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+3000,60000,peservcie);
    }
}
