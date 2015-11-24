package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.model.Event;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalendarActivity extends BaseActivity {
    public static final String TAG = CalendarActivity.class.getSimpleName();

    private ActionBarDrawerToggle mDrawerToggle;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.calendar_view)
    MaterialCalendarView mCalendarView;
    @Bind(R.id.fab)
    View mFab;
    @Bind(R.id.list)
    RecyclerView mRecyclerView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, CalendarActivity.class));
    }

    private Toolbar.OnMenuItemClickListener mMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.search_events:
                            SearchActivity.start(CalendarActivity.this);
                            return true;
                        case R.id.set_today:
                            mCalendarController.setToday();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getApplicationComponent().inject(this);
        }

        // Toolbarを初期化
        mToolbarHelper.init(this, mToolbar, R.string.no_string, mMenuItemClickListener);

        // DrawerToggle作成
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        // RecycleViewのAdapterを作成
        MyEventRecyclerViewAdapter eventRecyclerViewAdapter = new MyEventRecyclerViewAdapter(new ArrayList<Event>());

        // NavigationController作成
        mNavigationController.init(this, mDrawerToggle);
        ButterKnife.bind(mNavigationController, this);

        // EventController作成
        mEventController.init(getApplicationContext(), eventRecyclerViewAdapter);
        ButterKnife.bind(mEventController, this);

        // CalendarController作成
        mCalendarController.init(getApplicationContext(), eventRecyclerViewAdapter);
        ButterKnife.bind(mCalendarController, this);
    }

    @Override
    protected void onStart() {
        mNavigationController.onStart();
        mEventController.onStart();
        mCalendarController.onStart();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(mCalendarController);
        ButterKnife.unbind(mNavigationController);
        ButterKnife.unbind(mEventController);
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
