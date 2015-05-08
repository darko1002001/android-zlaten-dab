package com.aranea.apps.zlatendab.app;

import android.app.Application;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.util.AppUtil;
import com.aranea.apps.zlatendab.util.PreferenceUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtil.init(this);
        resolveLocale();
        setFont();
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

    private void setFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Blogger-Sans.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
