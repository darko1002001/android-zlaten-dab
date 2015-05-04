package com.aranea.apps.zlatendab;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.aranea.apps.zlatendab.util.ImageUtil;
import com.joanzapata.android.iconify.Iconify;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;
  @InjectView(R.id.fragmentContainer)
  FrameLayout fragmentContainer;

  public static final int OPTIONS_MENU_ITEM_SETTINGS = 321;
  public static final int OPTIONS_MENU_ITEM_INFO = 968;
    public static final int OPTIONS_MENU_ITEM_SHARE = 145;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    setupToolbar();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      MenuItem menuItemInfo = menu.add(0, OPTIONS_MENU_ITEM_INFO, 0, getString(R.string.action_info));
      menuItemInfo.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
      menuItemInfo.setIcon(ImageUtil.getIcon(getApplicationContext(), Iconify.IconValue.fa_info, android.R.color.white, 20));
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
              return true;
          case OPTIONS_MENU_ITEM_INFO:
              return true;
          case OPTIONS_MENU_ITEM_SHARE:
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
}
