package com.aranea.apps.zlatendab;

import android.app.Application;

import com.aranea.apps.zlatendab.util.AppUtil;
import com.aranea.apps.zlatendab.util.PreferenceUtil;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    PreferenceUtil.init(this);
    resolveLocale();
  }

  private void resolveLocale() {
    switch (PreferenceUtil.getLanguagePreference().get()) {
      case 0:
        AppUtil.changeLocale(this, "mk");
        break;
      case 1:
        AppUtil.changeLocale(this, "en");
        break;
    }
  }
}
