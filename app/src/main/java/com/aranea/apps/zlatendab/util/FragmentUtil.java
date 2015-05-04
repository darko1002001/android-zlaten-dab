package com.aranea.apps.zlatendab.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aranea.apps.zlatendab.R;


public class FragmentUtil {

    public static void replaceFragmentToLayout(final int containerId, final FragmentManager fragmentManager,
                                               final Fragment fragment, final String tag) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        final Fragment previousFragment = fragmentManager
                .findFragmentByTag(tag);
        if (previousFragment != null) {
            ft.remove(previousFragment);
        }
        ft.add(containerId, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    public static void replaceFragmentToLayoutWithAnimation(final int containerId, final FragmentManager fragmentManager,
                                                            final Fragment fragment, final String tag) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        final Fragment previousFragment = fragmentManager
                .findFragmentByTag(tag);
        if (previousFragment != null) {
            ft.remove(previousFragment);
        }
        ft.add(containerId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    public static void addFragmentToLayout(final int containerId, final FragmentManager fragmentManager,
                                           final Fragment fragment, final String tag) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(containerId, fragment, tag);
        ft.commit();
    }

    public static void removeDialogFragment(final String tag,
                                            final FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return;
        }
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        final Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null && prev.isAdded()) {
            ft.remove(prev);
        }
        ft.commitAllowingStateLoss();
    }


    public static void replaceFragment(FragmentManager manager, int parent,
                                       Fragment fragment) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(parent, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void removeFragment(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        final Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null) {
            ft.remove(fragment);
            ft.commit();
        }
    }

}