package com.kobaken0029.aizuplanner.view.controller.impl;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.activity.EventActivity;
import com.kobaken0029.aizuplanner.view.activity.SearchActivity;
import com.kobaken0029.aizuplanner.view.controller.NavigationController;

import java.lang.ref.WeakReference;

import butterknife.Bind;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class NavigationControllerImpl implements NavigationController {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private Activity mActivity;
    private ActionBarDrawerToggle mToggle;

    public void init(Activity activity, ActionBarDrawerToggle toggle) {
        mActivity = new WeakReference<>(activity).get();
        mToggle = toggle;
    }

    public void onStart() {
        mDrawerLayout.setDrawerListener(mToggle);
        mNavigationView.setNavigationItemSelectedListener(mItemSelectedListener);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private OnNavigationItemSelectedListener mItemSelectedListener =
            new OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_events_calendar:
                            menuItem.setChecked(true);
                            break;
                        case R.id.navigation_index_events:
                        case R.id.navigation_join_events:
                            EventActivity.start(mActivity);
                            break;
                        case R.id.navigation_search_events:
                            SearchActivity.start(mActivity);
                            break;
                        default:
                            break;
                    }
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            };
}
