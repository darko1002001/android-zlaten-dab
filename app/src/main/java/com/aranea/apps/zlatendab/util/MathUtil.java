package com.aranea.apps.zlatendab.util;

import java.text.DecimalFormat;

/**
 * Created by MephistoFloyd on 5/7/2015.
 */
public class MathUtil {

  public static final double OUNCES_CONVERTER_VALUE = 0.033814;
  public static final double POUNDS_CONVERTER_VALUE = 2.20462;
  public static final double MALE_ALCOHOL_CONSTANT = 0.78;
  public static final double FEMALE_ALCOHOL_CONSTANT = 0.66;
  public static final double LIQUID_OUNCES_TO_WEIGHT_CONSTANT = 5.14;
  public static final double AVERAGE_ALCOHOL_ELIMINATION_RATE = 0.015;
  public static final double BEER_ALCOHOL_PERCENT = 0.045;
  public static final double LEGAL_LIMIT_WITH_TWO_YEARS = 0.05;
  public static final double LEGAL_LIMIT_WITHOUT_TWO_YEARS = 0.00;

  public static double getPoundsFromKilograms(int kg) {
    return kg * POUNDS_CONVERTER_VALUE;
  }

  public static double convertTimeToHours(int hours, int minutes) {
    return hours + (minutes / 60);
  }

  public static double getOuncesFromMilliliters(int ml) {
    return ml * OUNCES_CONVERTER_VALUE;
  }

  public static double getLiquidOuncesOfAlcoholConsumed(double totalSmall, double totalMedium, double totalLarge) {
    return (totalSmall + totalMedium + totalLarge) * BEER_ALCOHOL_PERCENT;
  }

  public static double getBacLevel(double liquidOunces, double hours) {
    DecimalFormat df = new DecimalFormat("#.##");
    int weightKg = PreferenceUtil.getWeightPreference().get();
    int gender = PreferenceUtil.getGenderPreference().get();
    double genderConstant = 0;

    switch (gender) {
      case 0:
        genderConstant = MALE_ALCOHOL_CONSTANT;
        break;
      case 1:
        genderConstant = FEMALE_ALCOHOL_CONSTANT;
        break;
    }

    double result = Double.valueOf(df.format(((liquidOunces * LIQUID_OUNCES_TO_WEIGHT_CONSTANT) /
      (MathUtil.getPoundsFromKilograms(weightKg) * genderConstant)) -
      (AVERAGE_ALCOHOL_ELIMINATION_RATE * hours)));

    if (result < 0) {
      return Double.valueOf(df.format(0));
    } else return result;
  }

  public static double getLegalLimit() {
    return LEGAL_LIMIT_WITH_TWO_YEARS;
  }
}
