package com.aranea.apps.zlatendab;

import android.app.Application;

import com.aranea.apps.zlatendab.util.PreferenceUtil;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    PreferenceUtil.init(this);
  }
}
