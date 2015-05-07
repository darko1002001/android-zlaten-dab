package com.aranea.apps.zlatendab.modules.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aranea.apps.zlatendab.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by MephistoFloyd on 5/4/2015.
 */
public class MainFragment extends Fragment {

  @InjectView(R.id.pager)
  CustomViewPager pager;
  @InjectView(R.id.pager_container)
  PagerContainer pagerContainer;
  @InjectView(R.id.addButton)
  ImageButton addButton;
  @InjectView(R.id.rightButton)
  ImageButton rightButton;
  @InjectView(R.id.leftButton)
  ImageButton leftButton;
  @InjectView(R.id.amountLabel)
  TextView amountLabel;
  @InjectView(R.id.numberLarge)
  TextView numberLarge;
  @InjectView(R.id.numberLargeLayout)
  LinearLayout numberLargeLayout;
  @InjectView(R.id.numberMedium)
  TextView numberMedium;
  @InjectView(R.id.numberMediumLayout)
  LinearLayout numberMediumLayout;
  @InjectView(R.id.numberSmall)
  TextView numberSmall;
  @InjectView(R.id.numberSmallLayout)
  LinearLayout numberSmallLayout;
  @InjectView(R.id.intervalButton)
  Button intervalButton;
  @InjectView(R.id.bacLevel)
  TextView bacLevel;
  @InjectView(R.id.statusIcon)
  ImageButton statusIcon;

  private ViewPagerAdapter viewPagerAdapter;
  private int currentPosition;
  private int realPosition;

  private IconDrawable leftChevronDrawable;
  private IconDrawable rightChevronDrawable;
  private IconDrawable addDrawable;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, view);

    leftChevronDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_chevron_left)
      .colorRes(R.color.colorPrimary)
      .sizeDp(45);
    rightChevronDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_chevron_right)
      .colorRes(R.color.colorPrimary)
      .sizeDp(45);
    addDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_plus)
      .colorRes(R.color.textColorPrimary)
      .sizeDp(40);

    statusIcon.setImageDrawable(new IconDrawable(getActivity(), Iconify.IconValue.fa_taxi)
      .colorRes(R.color.taxiYellow)
      .sizeDp(25));

    rightButton.setImageDrawable(rightChevronDrawable);
    leftButton.setImageDrawable(leftChevronDrawable);
    addButton.setImageDrawable(addDrawable);
    leftButton.setOnClickListener(new OnButtonClickListener());
    rightButton.setOnClickListener(new OnButtonClickListener());
    addButton.setOnClickListener(new OnButtonClickListener());
    leftButton.setOnTouchListener(new OnButtonTouchListener());
    rightButton.setOnTouchListener(new OnButtonTouchListener());
    addButton.setOnTouchListener(new OnButtonTouchListener());

    pagerContainer = (PagerContainer) view.findViewById(R.id.pager_container);
    viewPagerAdapter = new ViewPagerAdapter(getActivity());
    currentPosition = viewPagerAdapter.getCount() / 2;
    pager = (CustomViewPager) pagerContainer.getViewPager();
    pager.setAdapter(viewPagerAdapter);
    pager.setPageTransformer(true, new PageTransformator());
    pager.setOffscreenPageLimit(5);
    pager.setCurrentItem(Integer.MAX_VALUE / 2);
    pager.setClipChildren(false);
    pager.setPageMargin(-200);
    pager.setScrollDurationFactor(3);

    pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        currentPosition = position;
        realPosition = position % viewPagerAdapter.getRealCount();
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        switch (state) {
          case ViewPager.SCROLL_STATE_IDLE:
            amountLabel.setVisibility(View.VISIBLE);
            switch (realPosition) {
              case 0:
                amountLabel.setText("0,33L");
                break;
              case 1:
                amountLabel.setText("0,5L");
                break;
              case 2:
                amountLabel.setText("1,5L");
                break;
            }
            break;
          case ViewPager.SCROLL_STATE_SETTLING:
            amountLabel.setVisibility(View.INVISIBLE);
            break;
        }
      }
    });

    amountLabel.setText("0,33L");

    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  private class OnButtonClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      if (view == leftButton) {
        pager.setCurrentItem(currentPosition - 1, true);
      } else if (view == rightButton) {
        pager.setCurrentItem(currentPosition + 1, true);
      } else if (view == addButton) {
        switch (realPosition) {
          case 0:
            break;
          case 1:
            break;
          case 2:
            break;
        }
      }
    }
  }

  private class OnButtonTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent event) {
      if (view == addButton) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            addButton.setImageDrawable(adjustIconOnPress(addButton));
            break;
          case MotionEvent.ACTION_UP:
            addButton.setImageDrawable(addDrawable);
            break;
        }
      } else if (view == leftButton) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            leftButton.setImageDrawable(adjustIconOnPress(leftButton));
            break;
          case MotionEvent.ACTION_UP:
            leftButton.setImageDrawable(leftChevronDrawable);
            break;
        }
      } else if (view == rightButton) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            rightButton.setImageDrawable(adjustIconOnPress(rightButton));
            break;
          case MotionEvent.ACTION_UP:
            rightButton.setImageDrawable(rightChevronDrawable);
            break;
        }
      }
      return false;
    }
  }

  private IconDrawable adjustIconOnPress(View view) {
    Iconify.IconValue value = null;

    if (view == addButton) {
      value = Iconify.IconValue.fa_plus;
    } else if (view == leftButton) {
      value = Iconify.IconValue.fa_chevron_left;
    } else if (view == rightButton) {
      value = Iconify.IconValue.fa_chevron_right;
    }

    if (view == addButton) {
      return new IconDrawable(getActivity(), value)
        .colorRes(R.color.textColorPrimary)
        .sizeDp(35);
    } else {
      return new IconDrawable(getActivity(), value)
        .colorRes(R.color.colorPrimary)
        .sizeDp(40);
    }
  }
}
