package com.aranea.apps.zlatendab;

import android.os.Handler;

public class Timer {

  private int secondsForTimer;
  private Handler handler;
  private Runnable runnable;

  private OnTimerSecondListener onTimerSecondListener;

  public void setOnTimerSecondListener(OnTimerSecondListener listener) {
    this.onTimerSecondListener = listener;
  }

  private Timer() {
  }

  public static Timer newInstance() {
    return new Timer();
  }

  public void startTimer(double countDownFrom) {
    secondsForTimer = (int) (countDownFrom * 3600);
    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        if (secondsForTimer > 0) handler.postDelayed(this, 1000);
        secondsForTimer--;
        final int seconds = secondsForTimer % 60;
        int totalMinutes = secondsForTimer / 60;
        final int minutes = totalMinutes % 60;
        final int hours = totalMinutes / 60;
        onTimerSecondListener.onSecond(hours, minutes, seconds);
      }
    };
    handler.postDelayed(runnable, 1000);
  }

  public void removeTimer() {
    if (handler == null) return;
    handler.removeCallbacks(runnable);
  }
}
