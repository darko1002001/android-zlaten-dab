package com.aranea.apps.zlatendab.modules.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.util.PreferenceUtil;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WarningDialogFragment extends DialogFragment {

    public static final String TAG = WarningDialogFragment.class.getSimpleName();

    @InjectView(R.id.textViewWarning)
    TextView textViewWarning;
    @InjectView(R.id.buttonOk)
    Button buttonWarning;

    private Activity context;

    public WarningDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_warning, null, false);
        ButterKnife.inject(this, view);

        initWarningText();

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private void initWarningText() {
        int yearsDriving = PreferenceUtil.getYearsDriving().get();

    }

}
