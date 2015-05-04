package com.aranea.apps.zlatendab;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    return images.length;
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
  public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
    ImageView imageView = null;
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
    imageView.setImageResource(this.getItem(i));
    return imageView;
  }
}
