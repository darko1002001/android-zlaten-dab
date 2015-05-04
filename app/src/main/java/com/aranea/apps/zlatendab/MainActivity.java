package com.aranea.apps.zlatendab;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;
  @InjectView(R.id.fragmentContainer)
  FrameLayout fragmentContainer;

  private FragmentManager fragmentManager;
  private FragmentTransaction transaction;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    setupToolbar();
    fragmentManager = getFragmentManager();
    Fragment fragment = new MainFragment();
    transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.fragmentContainer, fragment)
      .addToBackStack(null)
      .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.drawable.logo);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
  }
}
