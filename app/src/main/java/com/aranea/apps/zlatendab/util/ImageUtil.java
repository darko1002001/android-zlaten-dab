package com.aranea.apps.zlatendab.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

public class ImageUtil {


  public static void colorizeDrawable(Drawable drawable, int color) {
    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
  }

  public static Drawable getIcon(Context context, Iconify.IconValue iconValue, int color, int size) {
    return new IconDrawable(context, iconValue).colorRes(color).sizeDp(size);
  }

}
