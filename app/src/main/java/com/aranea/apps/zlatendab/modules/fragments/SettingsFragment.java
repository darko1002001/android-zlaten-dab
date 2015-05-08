package com.aranea.apps.zlatendab.modules.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.util.AppUtil;
import com.aranea.apps.zlatendab.util.PreferenceUtil;
import com.gc.materialdesign.views.Slider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingsFragment extends Fragment {

  @InjectView(R.id.textViewLabelGender)
  TextView textViewLabelGender;
  @InjectView(R.id.textViewLabelLanguage)
  TextView textViewLabelLanguage;
  @InjectView(R.id.radioButtonMale)
  RadioButton radioButtonMale;
  @InjectView(R.id.radioButtonFemale)
  RadioButton radioButtonFemale;
  @InjectView(R.id.imageButtonFlagUk)
  ImageButton imageButtonUk;
  @InjectView(R.id.imageButtonFlagMk)
  ImageButton imageButtonMk;
  @InjectView(R.id.slider)
  Slider slider;
  @InjectView(R.id.chosenWeightLabel)
  TextView chosenWeightLabel;
  @InjectView(R.id.textViewLabelWeight)
  TextView textViewLabelWeight;
  @InjectView(R.id.textViewLabelDrivingLicense)
  TextView textViewLabelDrivingLicense;
  @InjectView(R.id.radioButtonDrivingLicenseLess)
  RadioButton radioButtonDrivingLicenseLess;
  @InjectView(R.id.radioButtonDrivingLicenseMore)
  RadioButton radioButtonDrivingLicenseMore;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    slider.setValue(PreferenceUtil.getWeightPreference().get());
    chosenWeightLabel.setText(String.valueOf(PreferenceUtil.getWeightPreference().get()));
    slider.setOnValueChangedListener(new OnSliderValueChangedListener());
    setGender();
    setDrivingLicense();
    updateTextViews();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  @OnClick(R.id.radioButtonMale)
   public void changeGenderMale() {
    PreferenceUtil.getGenderPreference().set(0);
  }

  @OnClick(R.id.radioButtonFemale)
  public void changeGenderFemale() {
    PreferenceUtil.getGenderPreference().set(1);
  }

  @OnClick(R.id.radioButtonDrivingLicenseLess)
  public void changeDrivingLicenseLess() {
    PreferenceUtil.getYearsDriving().set(0);
  }

  @OnClick(R.id.radioButtonDrivingLicenseMore)
  public void changeDrivingLicenseMore() {
    PreferenceUtil.getYearsDriving().set(1);
  }


  @OnClick(R.id.imageButtonFlagUk)
  public void changeLocaleUk() {
    changeLocale("uk");
    PreferenceUtil.getLanguagePreference().set(1);
  }

  @OnClick(R.id.imageButtonFlagMk)
  public void changeLocaleMk() {
    changeLocale("mk");
    PreferenceUtil.getLanguagePreference().set(0);
  }

  private void changeLocale(String languageCode) {
    AppUtil.changeLocale(getActivity(), languageCode);
    updateTextViews();
  }

  private void updateTextViews() {
    radioButtonFemale.setText(getString(R.string.radio_button_female));
    radioButtonMale.setText(getString(R.string.radio_button_male));
    textViewLabelGender.setText(getString(R.string.label_gender));
    textViewLabelLanguage.setText(getString(R.string.label_language));
    textViewLabelWeight.setText(getString(R.string.label_weight));
    textViewLabelDrivingLicense.setText(getString(R.string.label_driving_license));
    radioButtonDrivingLicenseLess.setText(getString(R.string.radio_button_driving_license_less));
    radioButtonDrivingLicenseMore.setText(getString(R.string.radio_button_driving_license_more));

  }

  private class OnSliderValueChangedListener implements Slider.OnValueChangedListener {
    @Override
    public void onValueChanged(int value) {
      chosenWeightLabel.setText(String.valueOf(value));
      PreferenceUtil.getWeightPreference().set(value);
    }
  }

  private void setGender() {
    switch (PreferenceUtil.getGenderPreference().get()) {
      case 0:
        radioButtonMale.setChecked(true);
        break;
      case 1:
        radioButtonFemale.setChecked(true);
        break;
    }
  }

  private void setDrivingLicense() {
    switch (PreferenceUtil.getYearsDriving().get()) {
      case 0:
        radioButtonDrivingLicenseLess.setChecked(true);
        break;
      case 1:
        radioButtonDrivingLicenseMore.setChecked(true);
        break;
    }
  }
}