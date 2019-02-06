package com.example.android.homework.Utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.android.homework.R;


public class NotificationUtils {

    Context mContext;
    CharSequence mTitle;
    NotificationChannel channel;
    Notification notification;

    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotification(String title, String message, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mTitle = "News Update";
            String mDesc = "News update";
            channel = new NotificationChannel("NewsUpdate", mTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(mDesc);
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        PendingIntent mPendingIntent = PendingIntent.getService(
                mContext,
                0,
                intent,
                0
        );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "NewsUpdate");

        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker(title).setWhen(0)
                .setAutoCancel(false)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_android)
                .setContentText(message)
                .addAction(R.drawable.cancelic_, "Cancel", mPendingIntent)
                .build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(111, notification);
    }

    public void cancelNotification() {
        NotificationManagerCompat.from(mContext).cancel(111);
    }
}
