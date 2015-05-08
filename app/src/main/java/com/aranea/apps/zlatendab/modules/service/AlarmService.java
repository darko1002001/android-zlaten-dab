package com.aranea.apps.zlatendab.modules.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.modules.activities.MainActivity;

public class AlarmService extends IntentService {

    public static String NOTIFICATION_EXTRA = "notification_extra";
    private NotificationManager alarmNotificationManager;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification(getString(R.string.alarm_message));
    }

    private void sendNotification(String message) {
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NOTIFICATION_EXTRA, "notification_extra");

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle(getString(R.string.alarm_title)).setSmallIcon(R.drawable.notification_icon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message).setAutoCancel(true);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
    }
}
