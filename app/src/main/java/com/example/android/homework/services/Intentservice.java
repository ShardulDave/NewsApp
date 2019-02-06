package com.example.android.homework.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.android.homework.Utility.NotificationUtils;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;

public class Intentservice extends IntentService {
    FirebaseJobDispatcher firebaseJobDispatcher;
    NotificationUtils notificationUtility;
    public Intentservice() {
        super("not doing anything");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
        firebaseJobDispatcher.cancel("firebasejobservice");
        notificationUtility = new NotificationUtils(Intentservice.this);
        notificationUtility.cancelNotification();
    }
}
