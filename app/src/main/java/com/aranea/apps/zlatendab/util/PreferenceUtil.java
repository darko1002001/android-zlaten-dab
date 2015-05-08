package com.aranea.apps.zlatendab.util;

import com.aranea_apps.android.libs.commons.preferences.BasePreferenceUtil;

import info.metadude.android.typedpreferences.IntPreference;
import info.metadude.android.typedpreferences.StringPreference;

public class PreferenceUtil extends BasePreferenceUtil {

  public static final String TAG = PreferenceUtil.class.getSimpleName();
  private static final String KEY_LANGUAGE = "key_language";
  public static final String KEY_WEIGHT = "key_weight";
  public static final String KEY_YEARS_DRIVING = "key_years_driving";
  public static final String KEY_GENDER = "key_gender";
  public static final String KEY_SOBER_TIME = "key_sober_time";

  public static IntPreference getWeightPreference() {
    return intPreference(KEY_WEIGHT, 0);
  }

  public static IntPreference getYearsDriving() {
    return intPreference(KEY_YEARS_DRIVING, 0);
  }

  public static IntPreference getLanguagePreference() {
    return intPreference(KEY_LANGUAGE, 0);
  }

  public static IntPreference getGenderPreference() {
    return intPreference(KEY_GENDER, 0);
  }

  public static StringPreference getSoberTimePreference() {
    return stringPreference(KEY_SOBER_TIME, "");
  }
}