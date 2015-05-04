package com.aranea.apps.zlatendab.modules.views.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aranea.apps.zlatendab.modules.views.CoverFlow;

/**
 * Created by MephistoFloyd on 5/4/2015.
 */
public abstract class CoverFlowBaseAdapter extends BaseAdapter {

  @Override
  public final View getView(int i, View reusableView, ViewGroup viewGroup) {
    CoverFlow coverFlow = (CoverFlow) viewGroup;

    View wrappedView = null;
    CoverFlowItemWrapper coverFlowItem;

    if (reusableView != null) {
      coverFlowItem = (CoverFlowItemWrapper) reusableView;
      wrappedView = coverFlowItem.getChildAt(0);
      coverFlowItem.removeAllViews();
    } else {
      coverFlowItem = new CoverFlowItemWrapper(viewGroup.getContext());
    }

    wrappedView = this.getCoverFlowItem(i, wrappedView, viewGroup);

    if (wrappedView == null) {
      throw new NullPointerException("getCoverFlowItem() was expected to return a view, but null was returned.");
    }

    final boolean isReflectionEnabled = coverFlow.isReflectionEnabled();
    coverFlowItem.setReflectionEnabled(isReflectionEnabled);

    if(isReflectionEnabled) {
      coverFlowItem.setReflectionGap(coverFlow.getReflectionGap());
      coverFlowItem.setReflectionRatio(coverFlow.getReflectionRatio());
    }

    coverFlowItem.addView(wrappedView);
    coverFlowItem.setLayoutParams(wrappedView.getLayoutParams());

    return coverFlowItem;
  }

  public abstract View getCoverFlowItem(int position, View reusableView, ViewGroup parent);
}
