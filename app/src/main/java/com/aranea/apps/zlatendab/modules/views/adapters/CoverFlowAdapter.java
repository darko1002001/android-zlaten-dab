package com.aranea.apps.zlatendab.modules.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.modules.views.CoverFlow;
import com.aranea.apps.zlatendab.util.AppUtil;

/**
 * Created by MephistoFloyd on 5/4/2015.
 */
public class CoverFlowAdapter extends CoverFlowBaseAdapter {

  private int[] images = {R.drawable.bottle033a, R.drawable.bottle033b, R.drawable.bottle033c};
  private Context context;

  public CoverFlowAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return Integer.MAX_VALUE;
  }

  @Override
  public Integer getItem(int i) {
    return images[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getCoverFlowItem(int position, View reuseableView, ViewGroup viewGroup) {
    ImageView imageView = null;

    if (position >= images.length) {
      position = position % images.length;
    }

    float widthInDp = 150;
    float heightInDp = 250;

    if (reuseableView != null) {
      imageView = (ImageView) reuseableView;
    } else {
      imageView = new ImageView(viewGroup.getContext());
      imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      imageView.setLayoutParams(new CoverFlow.LayoutParams((int) AppUtil.convertDpToPixel(widthInDp, context),
        (int) AppUtil.convertDpToPixel(heightInDp, context)));
    }
    imageView.setImageResource(this.getItem(position));
    return imageView;
  }
}
