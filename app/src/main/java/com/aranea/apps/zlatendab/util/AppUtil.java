package com.aranea.apps.zlatendab.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by MephistoFloyd on 5/4/2015.
 */
public class AppUtil {

  public static float convertDpToPixel(float dp, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float px = dp * (metrics.densityDpi / 160f);
    return px;
  }

  public static float convertPixelsToDp(float px, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float dp = px / (metrics.densityDpi / 160f);
    return dp;
  }

  public static void changeLocale(Context context, String languageCode) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    Configuration configuration = context.getResources().getConfiguration();

    configuration.locale = new Locale(languageCode.toLowerCase());
    context.getResources().updateConfiguration(configuration, dm);
  }
}
