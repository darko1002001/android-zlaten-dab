package com.aranea.apps.zlatendab.modules.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aranea.apps.zlatendab.modules.views.CoverFlow;
import com.aranea.apps.zlatendab.modules.views.adapters.CoverFlowAdapter;
import com.aranea.apps.zlatendab.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by MephistoFloyd on 5/4/2015.
 */
public class MainFragment extends Fragment {

  @InjectView(R.id.coverFlow)
  CoverFlow coverFlow;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, view);

    coverFlow.setAdapter(new CoverFlowAdapter(getActivity()));
    coverFlow.setSelection(Integer.MAX_VALUE / 2);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
