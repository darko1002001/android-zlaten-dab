package com.aranea.apps.zlatendab.modules.fragments;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;


import com.aranea.apps.zlatendab.R;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingsFragment extends Fragment {

    @InjectView(R.id.textViewGender)
    TextView textViewLabelGender;
    @InjectView(R.id.textViewLanguage)
    TextView textViewLabelLanguage;
    @InjectView(R.id.radioButtonMale)
    RadioButton radioButtonMale;
    @InjectView(R.id.radioButtonFemale)
    RadioButton radioButtonFemale;
    @InjectView(R.id.imageButtonFlagUk)
    ImageButton imageButtonUk;
    @InjectView(R.id.imageButtonFlagMk)
    ImageButton imageButtonMk;
    @InjectView(R.id.editTextWeight)
    EditText editTextWeight;

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
        updateTextViews();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.imageButtonFlagUk)
    public void changeLocaleUk() {
        changeLocale("uk");
    }

    @OnClick(R.id.imageButtonFlagMk)
    public void changeLocaleMk() {
        changeLocale("mk");
    }

    private void changeLocale(String languageCode) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();

        configuration.locale = new Locale(languageCode.toLowerCase());
        getResources().updateConfiguration(configuration, dm);
        updateTextViews();
    }

    private void updateTextViews() {
        radioButtonFemale.setText(getString(R.string.radio_button_female));
        radioButtonMale.setText(getString(R.string.radio_button_male));
        textViewLabelGender.setText(getString(R.string.label_gender));
        editTextWeight.setHint(getString(R.string.edit_text_hint_weight));
        textViewLabelLanguage.setText(getString(R.string.label_language));
    }
}