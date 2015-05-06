package com.aranea.apps.zlatendab.modules.fragments.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aranea.apps.zlatendab.R;

public class ViewPagerAdapter extends PagerAdapter {

  Context context;
  private int[] images = new int[]{
    R.drawable.bottle033a,
    R.drawable.bottle033b,
    R.drawable.bottle033c
  };

  public int getRealCount() {
    return images.length;
  }

  ViewPagerAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return Integer.MAX_VALUE;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((ImageView) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    if (position >= images.length) {
      position = position % images.length;
    }
    final ImageView imageView = new ImageView(context);
    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    imageView.setImageResource(images[position]);
    ((ViewPager) container).addView(imageView, 0);
    return imageView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    ((ViewPager) container).removeView((ImageView) object);
  }
}
