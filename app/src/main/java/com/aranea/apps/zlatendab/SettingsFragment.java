package com.aranea.apps.zlatendab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;


import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class SettingsFragment extends Fragment {

    @InjectView(R.id.radioButtonMale)
    RadioButton radioButtonMale;
    @InjectView(R.id.radioButtonFemale)
    RadioButton radioButtonFemale;
    @InjectView(R.id.radioButtonEnglish)
    RadioButton radioButtonEnglish;
    @InjectView(R.id.radioButtonMacedonian)
    RadioButton radioButtonMacedonian;
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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}