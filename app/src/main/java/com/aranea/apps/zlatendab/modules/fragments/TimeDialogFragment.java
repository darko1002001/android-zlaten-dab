package com.aranea.apps.zlatendab.modules.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.aranea.apps.zlatendab.R;
import com.gc.materialdesign.views.Slider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by MephistoFloyd on 5/7/2015.
 */
public class TimeDialogFragment extends DialogFragment {

  @InjectView(R.id.sliderHours)
  Slider sliderHours;
  @InjectView(R.id.chosenHoursLabel)
  TextView chosenHoursLabel;
  @InjectView(R.id.sliderMinutes)
  Slider sliderMinutes;
  @InjectView(R.id.chosenMinutesLabel)
  TextView chosenMinutesLabel;
  @InjectView(R.id.confirmButton)
  Button confirmButton;

  private int hours = 0;
  private int minutes = 0;

  private OnTimeChosenListener onTimeChosenListener;

  public interface OnTimeChosenListener {
     void onChoose(int hours, int minutes);
  }

  public void setOnTimeChosenListener(OnTimeChosenListener listener) {
    this.onTimeChosenListener = listener;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_fragment_choose_time, container, false);
    ButterKnife.inject(this, view);

    sliderHours.setOnValueChangedListener(new Slider.OnValueChangedListener() {
      @Override
      public void onValueChanged(int value) {
        hours = value;
        chosenHoursLabel.setText(String.valueOf(value));
      }
    });

    sliderMinutes.setOnValueChangedListener(new Slider.OnValueChangedListener() {
      @Override
      public void onValueChanged(int value) {
        minutes = value;
        chosenMinutesLabel.setText(String.valueOf(value));
      }
    });

    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onTimeChosenListener.onChoose(hours, minutes);
        dismiss();
      }
    });

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);


    return dialog;
  }
}
