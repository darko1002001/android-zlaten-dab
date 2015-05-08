package com.aranea.apps.zlatendab.modules.fragments.main;

import android.app.Activity;
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

import com.aranea.apps.zlatendab.OnTimerSecondListener;
import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.Timer;
import com.aranea.apps.zlatendab.modules.TimeDialogFragment;
import com.aranea.apps.zlatendab.modules.fragments.TaxiDialogFragment;
import com.aranea.apps.zlatendab.modules.activities.MainActivity;
import com.aranea.apps.zlatendab.util.AppUtil;
import com.aranea.apps.zlatendab.util.MathUtil;
import com.aranea.apps.zlatendab.util.PreferenceUtil;
import com.gc.materialdesign.widgets.SnackBar;
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
  @InjectView(R.id.addBeersLabel)
  TextView addBeersLabel;
  @InjectView(R.id.beerNumbersLayout)
  LinearLayout beerNumbersLayout;
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
  @InjectView(R.id.statusButton)
  ImageButton statusButton;
  @InjectView(R.id.calculateButton)
  Button calculateButton;
  @InjectView(R.id.alarmButton)
  Button alarmButton;
  @InjectView(R.id.resetButton)
  Button resetButton;
  @InjectView(R.id.timer)
  TextView timerLabel;

  private OnGoToSettingsListener onGoToSettingsListener;

  public interface OnGoToSettingsListener {
    public void onOpenSettings();
  }

  private ViewPagerAdapter viewPagerAdapter;
  private int currentPosition;
  private int realPosition;

  private IconDrawable leftChevronDrawable;
  private IconDrawable rightChevronDrawable;
  private IconDrawable addDrawable;
  private IconDrawable statusDrawableTaxi;
  private IconDrawable statusDrawableOk;

  private int smallBeersNumber;
  private int mediumBeersNumber;
  private int largeBeersNumbers;

  private int hoursChosen;
  private int minutesChosen;

  private TimeDialogFragment timeDialogFragment;
  private SnackBar snackBar;
  private Timer timer;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, view);

    initializeLayout(view);

    timeDialogFragment = new TimeDialogFragment();
    timeDialogFragment.setOnTimeChosenListener(new TimeDialogFragment.OnTimeChosenListener() {
      @Override
      public void onChoose(int hours, int minutes) {
        intervalButton.setText(hours + " " + getString(R.string.hours) + ",\n" + minutes + " " + getString(R.string.minutes));
        hoursChosen = hours;
        minutesChosen = minutes;
      }
    });

    timer = Timer.newInstance();
    timer.setOnTimerSecondListener(new OnTimerSecondListener() {
      @Override
      public void onSecond(int hours, int minutes, int seconds) {
        timerLabel.setText(hours + ":" + minutes + ":" + seconds);
      }
    });

    if (PreferenceUtil.getSoberTimePreference().get() != null) {
      timer.startTimer(MathUtil.refreshTimeUntilSober());
    }

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
                amountLabel.setText(getString(R.string.small));
                break;
              case 1:
                amountLabel.setText(getString(R.string.medium));
                break;
              case 2:
                amountLabel.setText(getString(R.string.large));
                break;
            }
            break;
          case ViewPager.SCROLL_STATE_SETTLING:
            amountLabel.setVisibility(View.INVISIBLE);
            break;
        }
      }
    });

    amountLabel.setText(getString(R.string.small));
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
    timer.removeTimer();
  }

  public void onAttach(Activity activity) {
    super.onAttach(activity);
    onGoToSettingsListener = (MainActivity) getActivity();
  }

  private class OnButtonClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      if (view == leftButton) {
        pager.setCurrentItem(currentPosition - 1, true);
      } else if (view == rightButton) {
        pager.setCurrentItem(currentPosition + 1, true);
      } else if (view == addButton) {
        addBeersLabel.setVisibility(View.GONE);
        beerNumbersLayout.setVisibility(View.VISIBLE);
        switch (realPosition) {
          case 0:
            numberSmall.setText(String.valueOf(++smallBeersNumber));
            break;
          case 1:
            numberMedium.setText(String.valueOf(++mediumBeersNumber));
            break;
          case 2:
            numberLarge.setText(String.valueOf(++largeBeersNumbers));
            break;
        }
      } else if (view == intervalButton) {
        timeDialogFragment.show(getFragmentManager(), "Time");
      } else if (view == statusButton) {
        showCabDialog();
      } else if (view == calculateButton) {
        calculateBacLevel();
      } else if (view == alarmButton) {

      } else if (view == resetButton) {
        PreferenceUtil.getSoberTimePreference().delete();
        if (timer != null) timer.removeTimer();
        initForms();
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
      } else if (view == statusButton) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            statusButton.setImageDrawable(adjustIconOnPress(statusButton));
            break;
          case MotionEvent.ACTION_UP:
            statusButton.setImageDrawable(statusDrawableTaxi);
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
    } else if (view == statusButton) {
      value = Iconify.IconValue.fa_taxi;
    }

    if (view == addButton) {
      return new IconDrawable(getActivity(), value)
        .colorRes(R.color.textColorPrimary)
        .sizeDp(35);
    } else if (view == statusButton) {
      return new IconDrawable(getActivity(), value)
        .colorRes(R.color.taxiYellow)
        .sizeDp(20);
    } else {
      return new IconDrawable(getActivity(), value)
        .colorRes(R.color.colorPrimary)
        .sizeDp(40);
    }
  }

  private void initializeLayout(View view) {
    leftChevronDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_chevron_left)
      .colorRes(R.color.colorPrimary)
      .sizeDp(45);
    rightChevronDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_chevron_right)
      .colorRes(R.color.colorPrimary)
      .sizeDp(45);
    addDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_plus)
      .colorRes(R.color.textColorPrimary)
      .sizeDp(40);
    statusDrawableTaxi = new IconDrawable(getActivity(), Iconify.IconValue.fa_taxi)
      .colorRes(R.color.taxiYellow)
      .sizeDp(25);
    statusDrawableOk = new IconDrawable(getActivity(), Iconify.IconValue.fa_check)
      .colorRes(R.color.zlatenDab)
      .sizeDp(25);
    statusButton.setEnabled(false);
    statusButton.setOnClickListener(new OnButtonClickListener());
    statusButton.setOnTouchListener(new OnButtonTouchListener());

    rightButton.setImageDrawable(rightChevronDrawable);
    leftButton.setImageDrawable(leftChevronDrawable);
    addButton.setImageDrawable(addDrawable);
    leftButton.setOnClickListener(new OnButtonClickListener());
    rightButton.setOnClickListener(new OnButtonClickListener());
    addButton.setOnClickListener(new OnButtonClickListener());
    leftButton.setOnTouchListener(new OnButtonTouchListener());
    rightButton.setOnTouchListener(new OnButtonTouchListener());
    addButton.setOnTouchListener(new OnButtonTouchListener());
    calculateButton.setOnClickListener(new OnButtonClickListener());
    alarmButton.setOnClickListener(new OnButtonClickListener());
    intervalButton.setOnClickListener(new OnButtonClickListener());
    resetButton.setOnClickListener(new OnButtonClickListener());

    pagerContainer = (PagerContainer) view.findViewById(R.id.pager_container);
    viewPagerAdapter = new ViewPagerAdapter(getActivity());
    currentPosition = viewPagerAdapter.getCount() / 2;
    pager = (CustomViewPager) pagerContainer.getViewPager();
    pager.setAdapter(viewPagerAdapter);
    pager.setPageTransformer(true, new PageTransformator());
    pager.setOffscreenPageLimit(5);
    pager.setCurrentItem(Integer.MAX_VALUE / 2);
    pager.setClipChildren(false);
    pager.setPageMargin(AppUtil.convertDpToPixel(-60, getActivity()));
    pager.setScrollDurationFactor(3);

    initForms();
  }

  private void calculateBacLevel() {

    if (PreferenceUtil.getWeightPreference().get() == 0) {
      snackBar = new SnackBar(getActivity(), getString(R.string.weight_warning), getString(R.string.snack_settings), new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onGoToSettingsListener.onOpenSettings();
        }
      });
      snackBar.setColorButton(getResources().getColor(R.color.zlatenDab));
      snackBar.setMessageTextSize(AppUtil.convertDpToPixel(7, getActivity()));
      snackBar.setDismissTimer(10000);
      snackBar.show();
      return;
    }

    if (smallBeersNumber == 0 && mediumBeersNumber == 0 && largeBeersNumbers == 0) {
      snackBar = new SnackBar(getActivity(), getString(R.string.drinks_warning), getString(R.string.ok), new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          snackBar.dismiss();
        }
      });
      snackBar.setColorButton(getResources().getColor(R.color.zlatenDab));
      snackBar.setMessageTextSize(AppUtil.convertDpToPixel(7, getActivity()));
      snackBar.setDismissTimer(10000);
      snackBar.show();
      return;
    }

    if (hoursChosen == 0 && minutesChosen == 0) {
      snackBar = new SnackBar(getActivity(), getString(R.string.time_warning), getString(R.string.ok), new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          snackBar.dismiss();
        }
      });
      snackBar.setColorButton(getResources().getColor(R.color.zlatenDab));
      snackBar.setMessageTextSize(AppUtil.convertDpToPixel(7, getActivity()));
      snackBar.setDismissTimer(10000);
      snackBar.show();
      return;
    }

    int numSmallBeers = Integer.parseInt(numberSmall.getText().toString());
    int numMediumBeers = Integer.parseInt(numberMedium.getText().toString());
    int numLargeBeers = Integer.parseInt(numberLarge.getText().toString());

    double totalSmall = numSmallBeers * MathUtil.getOuncesFromMilliliters(330);
    double totalMedium = numMediumBeers * MathUtil.getOuncesFromMilliliters(500);
    double totalLarge = numLargeBeers * MathUtil.getOuncesFromMilliliters(1500);

    double ouncesConsumed = MathUtil.getLiquidOuncesOfAlcoholConsumed(totalSmall,
      totalMedium,
      totalLarge);

    double timeInHours = MathUtil.convertTimeToHours(hoursChosen, minutesChosen);
    double resultBac = MathUtil.getBacLevel(ouncesConsumed, timeInHours);
    MathUtil.calculateAndSaveSoberTime(MathUtil.getHoursUntilSober(resultBac));
    timer.startTimer(MathUtil.getHoursUntilSober(resultBac));
    bacLevel.setText(String.valueOf(resultBac) + "%");

    statusButton.setVisibility(View.VISIBLE);
    if (resultBac > MathUtil.getLegalLimit()) {
      statusButton.setEnabled(true);
      bacLevel.setTextColor(getResources().getColor(R.color.red));
      statusButton.setImageDrawable(statusDrawableTaxi);
    } else {
      bacLevel.setTextColor(getResources().getColor(R.color.textColorPrimary));
      statusButton.setImageDrawable(statusDrawableOk);
    }
  }

  private void initForms() {
    hoursChosen = 0;
    minutesChosen = 0;
    smallBeersNumber = 0;
    mediumBeersNumber = 0;
    largeBeersNumbers = 0;
    intervalButton.setText(hoursChosen + " " + getString(R.string.hours) + ",\n" + minutesChosen + " " + getString(R.string.minutes));
    bacLevel.setTextColor(getResources().getColor(R.color.textColorPrimary));
    addBeersLabel.setVisibility(View.VISIBLE);
    beerNumbersLayout.setVisibility(View.GONE);
    statusButton.setVisibility(View.INVISIBLE);
    numberSmall.setText("0");
    numberMedium.setText("0");
    numberLarge.setText("0");
    bacLevel.setText("0.0%");
    timerLabel.setText("00:00:00");
  }

  private void showCabDialog() {
    TaxiDialogFragment taxiDialogFragment = new TaxiDialogFragment();
    taxiDialogFragment.show(getActivity().getSupportFragmentManager(), TaxiDialogFragment.TAG);
  }
}
