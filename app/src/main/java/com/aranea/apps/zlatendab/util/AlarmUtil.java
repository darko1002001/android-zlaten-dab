package com.aranea.apps.zlatendab.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmUtil {

  public static void setAlarm(Context context, int milliseconds) {
    AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    Intent intent = new Intent(context, AlarmReceiver.class);

    PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
    am.set(AlarmManager.RTC, System.currentTimeMillis() + milliseconds, pi);
  }

  public static void cancelAlarm(Context context) {
    AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    Intent intent = new Intent(context, AlarmReceiver.class);

    PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

    am.cancel(pi);
  }
}
