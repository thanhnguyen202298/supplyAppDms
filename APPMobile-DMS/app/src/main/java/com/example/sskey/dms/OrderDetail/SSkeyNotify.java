package com.example.sskey.dms.OrderDetail;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.example.sskey.dms.MainActivity;
import com.example.sskey.dms.R;
import com.sskey.dms.utils.Constants;

public class SSkeyNotify implements InotifySskey {

    private Notification.Builder notifyBuider;
    private static NotificationManager notiMaa;
    private Notification mnotification;
    private String ChannelID = "TSskeyNotify";
    private static int NotifyID = 12579;
    private String ChannelName = "TSskeyNotify";
    private String ChannelDescription = "TSskeyNotify đơn hàng Gas Việt";
    private Context ctx;

    public SSkeyNotify(Context context) {
        ctx = context;
        Snotification = this;
    }

    public static InotifySskey Snotification;


    @Override
    public int getNotifyID() {
        return NotifyID;
    }

    public static NotificationManager getNotifMan() {
        return notiMaa;
    }

    @Override
    public Notification getNotification() {
        return mnotification;
    }

    @Override
    public void showNotify() {
        notiMaa.notify(NotifyID, notifyBuider.build());
    }

    @Override
    public void setUpNotiSsk(String notifyText) {
        boolean not8 = true;
        Notification.BigPictureStyle style = new Notification.BigPictureStyle();
        if (Build.VERSION.SDK_INT >= 26) {
            notifyBuider = new Notification.Builder(ctx, ChannelID);
            NotificationChannel channel = new NotificationChannel(ChannelID, ChannelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(ChannelDescription);

            notiMaa = ctx.getSystemService(NotificationManager.class);
            notiMaa.createNotificationChannel(channel);
            Icon icon = Icon.createWithResource(ctx, R.drawable.ic_launcher);
            style.bigLargeIcon(icon);
        } else {
            not8 = false;
            notifyBuider = new Notification.Builder(ctx);
            notiMaa = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notifyBuider.setSmallIcon(R.drawable.ic_launcher)
                .setContentText(notifyText)
                .setStyle(style.setBigContentTitle(notifyText))
                .setAutoCancel(true).setSound(alarmSound);;

        setActionNotiSSk(not8);
        setPendingNotiSSk(not8, notifyText);
        mnotification = notifyBuider.build();
    }

    @Override
    public void setUpBlk() {
        boolean not8 = true;
        if (Build.VERSION.SDK_INT >= 26) {
            notifyBuider = new Notification.Builder(ctx, ChannelID);
            notifyBuider.setBadgeIconType(Notification.BADGE_ICON_NONE);

            NotificationChannel channel = new NotificationChannel(ChannelID, ChannelName, NotificationManager.IMPORTANCE_NONE);
            channel.setDescription(ChannelDescription);
            channel.setShowBadge(false);
            notiMaa = ctx.getSystemService(NotificationManager.class);
            notiMaa.createNotificationChannel(channel);
        } else {
            not8 = false;
            notifyBuider = new Notification.Builder(ctx);
            notiMaa = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        notifyBuider.setSmallIcon(R.drawable.ic_gasem)
                .setAutoCancel(true).setSound(null);

        mnotification = notifyBuider.build();
    }

    @Override
    public void setStyleNotiSSk() {

    }

    @Override
    public void setPendingNotiSSk(boolean noti8, String notifyText) {
        Intent it = new Intent(ctx.getApplicationContext(), MainActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        it.putExtra(Constants.StartFromDetail, "");
        it.putExtra(Constants.StartFromNotify, "");
        Intent[] mit = new Intent[1];
        mit[0] = it;
        PendingIntent pe = PendingIntent.getActivities(ctx.getApplicationContext(), NotifyID, mit, PendingIntent.FLAG_CANCEL_CURRENT);

        notifyBuider.setContentIntent(pe);
    }

    @Override
    public void setActionNotiSSk(boolean noti8) {

    }
}
