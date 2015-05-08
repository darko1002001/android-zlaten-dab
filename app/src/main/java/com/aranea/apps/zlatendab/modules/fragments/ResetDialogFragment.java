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

import com.aranea.apps.zlatendab.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by MephistoFloyd on 5/7/2015.
 */
public class ResetDialogFragment extends DialogFragment {

  @InjectView(R.id.confirmButton)
  Button confirmButton;

  private OnOkClickListener onOkClickListener;

  public interface OnOkClickListener {
    public void onOk();
  }

  public void setOnOkClickListener(OnOkClickListener listener) {
    this.onOkClickListener = listener;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_fragment_reset, container, false);
    ButterKnife.inject(this, view);

    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onOkClickListener.onOk();
        dismiss();
      }
    });

    return view;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }
}
