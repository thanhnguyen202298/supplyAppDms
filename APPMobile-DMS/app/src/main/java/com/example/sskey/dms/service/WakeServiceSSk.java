package com.example.sskey.dms.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WakeServiceSSk {
    public static String MYSERVICE_ACTION = "com.example.sskey.testserver.StartmyFirstSSK";
    public static String MYBOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
    public static String MYPACKAGE_ACTION = "android.intent.action.PACKAGE_ADDED";
    public static BootEventReceiver serviceReceiver = new BootEventReceiver();

    public WakeServiceSSk() {

    }

    public static void onWWake(Context context) {
//        if (Build.VERSION.SDK_INT > 26) {
//            if (context.checkSelfPermission(Manifest.permission.RECEIVE_BOOT_COMPLETED.toString()) > 0) {
//
//            }
//        }
        Log.e("<<Alarm", "start in alarm");

//        IntentFilter broadfilter = new IntentFilter(MYSERVICE_ACTION);
//        context.registerReceiver(serviceReceiver, broadfilter);
//
//        Intent it = new Intent(context, TestService.class);
////        Intent it = new Intent(MYSERVICE_ACTION);
//        PendingIntent peservcie = PendingIntent.getService(context, TestService.SServiceID, it, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alm.cancel(peservcie);
//        alm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, 60000, peservcie);

//        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP)
//            alm.setAlarmClock(new AlarmManager.AlarmClockInfo(System.currentTimeMillis()+2000,peservcie),peservcie);
//        else alm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+2000, peservcie);

    }
}