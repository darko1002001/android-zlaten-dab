package com.aranea.apps.zlatendab.modules.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aranea.apps.zlatendab.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TaxiDialogFragment extends DialogFragment {

    public static final String TAG = TaxiDialogFragment.class.getSimpleName();
    public static String TAXI_GLOBAL = "070400023";
    public static String TAXI_RIVA = "070971916";
    public static String TAXI_PULSTARS = "071397939";

    @InjectView(R.id.linearLayoutGlobal)
    LinearLayout linearLayoutGlobal;
    @InjectView(R.id.linearLayoutRiva)
    LinearLayout linearLayoutRiva;
    @InjectView(R.id.linearLayoutPulstars)
    LinearLayout linearLayoutPulstars;

    @InjectView(R.id.imageViewGlobal)
    ImageView imageViewGlobal;
    @InjectView(R.id.imageViewRiva)
    ImageView imageViewRiva;
    @InjectView(R.id.imageViewPulstars)
    ImageView imageViewPulstars;

    private Activity context;

    public TaxiDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_taxi, null, false);
        ButterKnife.inject(this, view);

        imageViewGlobal.setImageDrawable(new IconDrawable(getActivity(), Iconify.IconValue.fa_taxi).colorRes(android.R.color.holo_red_light).sizeDp(25));
        imageViewRiva.setImageDrawable(new IconDrawable(getActivity(), Iconify.IconValue.fa_taxi).colorRes(android.R.color.holo_blue_light).sizeDp(25));
        imageViewPulstars.setImageDrawable(new IconDrawable(getActivity(), Iconify.IconValue.fa_taxi).colorRes(android.R.color.holo_green_light).sizeDp(25));

        linearLayoutGlobal.setOnClickListener(new OnGlobalClickListener());
        linearLayoutRiva.setOnClickListener(new OnRivaClickListener());
        linearLayoutPulstars.setOnClickListener(new OnPulstarsClickListener());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private final class OnGlobalClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            callCab(TAXI_GLOBAL);
        }
    }

    private final class OnRivaClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            callCab(TAXI_RIVA);

        }
    }

    private final class OnPulstarsClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            callCab(TAXI_PULSTARS);
        }
    }

    private void callCab(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
        this.dismiss();
    }

}
