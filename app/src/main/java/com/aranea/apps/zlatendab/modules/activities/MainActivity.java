package com.aranea.apps.zlatendab.modules.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aranea.apps.zlatendab.R;
import com.aranea.apps.zlatendab.modules.fragments.SettingsFragment;
import com.aranea.apps.zlatendab.modules.fragments.main.MainFragment;
import com.aranea.apps.zlatendab.modules.fragments.main.ManageAlarmListener;
import com.aranea.apps.zlatendab.modules.service.AlarmBroadcastReceiver;
import com.aranea.apps.zlatendab.util.FragmentUtil;
import com.aranea.apps.zlatendab.util.ImageUtil;
import com.joanzapata.android.iconify.Iconify;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends ActionBarActivity implements MainFragment.OnGoToSettingsListener, ManageAlarmListener {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;

  private FragmentManager fragmentManager;
  private AlarmManager alarmManager;
  private Intent mNotificationReceiverIntent;
  private PendingIntent mNotificationReceiverPendingIntent;
  private static final long INITIAL_ALARM_DELAY = 2 * 60 * 1000L;
  protected static final long JITTER = 5000L;

  public static final int OPTIONS_MENU_ITEM_SETTINGS = 321;
  public static final int OPTIONS_MENU_ITEM_INFO = 968;
  public static final int OPTIONS_MENU_ITEM_SHARE = 145;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    fragmentManager = getSupportFragmentManager();
    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    // Create an Intent to broadcast to the AlarmNotificationReceiver
    mNotificationReceiverIntent = new Intent(MainActivity.this,
            AlarmBroadcastReceiver.class);

    // Create an PendingIntent that holds the NotificationReceiverIntent
    mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
            MainActivity.this, 0, mNotificationReceiverIntent, 0);

    setupToolbar();

    FragmentUtil.replaceFragment(fragmentManager,
      R.id.fragmentContainer, new MainFragment(), MainFragment.class.getSimpleName());


  }

  @Override
  protected void onResume() {
    super.onResume();
    checkAlarm();
  }

  @Override
  public void onBackPressed() {
    if (fragmentManager.getBackStackEntryCount() == 1) {
      finish();
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuItem menuItemShare = menu.add(0, OPTIONS_MENU_ITEM_SHARE, 0, getString(R.string.action_share));
    menuItemShare.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menuItemShare.setIcon(ImageUtil.getIcon(getApplicationContext(), Iconify.IconValue.fa_share_alt, android.R.color.white, 20));
    MenuItem menuItemSettings = menu.add(0, OPTIONS_MENU_ITEM_SETTINGS, 0, getString(R.string.action_settings));
    menuItemSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menuItemSettings.setIcon(ImageUtil.getIcon(getApplicationContext(), Iconify.IconValue.fa_gear, android.R.color.white, 20));
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case OPTIONS_MENU_ITEM_SETTINGS:
        if (fragmentManager.findFragmentByTag(SettingsFragment.class.getSimpleName()) == null)
          FragmentUtil.replaceFragment(fragmentManager,
            R.id.fragmentContainer, new SettingsFragment(), SettingsFragment.class.getSimpleName());
        return true;
      case OPTIONS_MENU_ITEM_SHARE:
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://zlatendab.com.mk/");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.drawable.logo);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  public void onOpenSettings() {
    if (fragmentManager.findFragmentByTag(SettingsFragment.class.getSimpleName()) == null)
      FragmentUtil.replaceFragment(getSupportFragmentManager(),
        R.id.fragmentContainer, new SettingsFragment(), SettingsFragment.class.getSimpleName());
  }

  @Override
  public void startAlarm(long millis) {
    alarmManager.set(AlarmManager.RTC_WAKEUP,
            millis, mNotificationReceiverPendingIntent);
  }

  @Override
  public void cancelAlarm() {
    alarmManager.cancel(mNotificationReceiverPendingIntent);
  }

  private void checkAlarm() {
    Log.d("debug", "check alarm");
    if (getIntent().getExtras() != null) {
        Log.d("debug", "canceling alarm");
        cancelAlarm();

    }
  }

}
