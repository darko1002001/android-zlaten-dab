package com.aranea.apps.zlatendab.modules.fragments.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * Created by MephistoFloyd on 5/6/2015.
 */
public class CustomViewPager extends ViewPager {

  private ScrollerCustomDuration scroller = null;

  public CustomViewPager(Context context) {
    super(context);
    postInitViewPager();
  }

  public CustomViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    postInitViewPager();
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }

  private void postInitViewPager() {
    try {
      Field scroller = ViewPager.class.getDeclaredField("mScroller");
      scroller.setAccessible(true);
      Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
      interpolator.setAccessible(true);

      this.scroller = new ScrollerCustomDuration(getContext(),
        (Interpolator) interpolator.get(null));
      scroller.set(this, this.scroller);
    } catch (Exception e) {
    }
  }

  public void setScrollDurationFactor(double scrollFactor) {
    scroller.setScrollDurationFactor(scrollFactor);
  }
}
