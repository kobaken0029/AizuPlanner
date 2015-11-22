package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {
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

    @OnClick(R.id.fab)
    void onClickFab() {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, CalendarActivity.class));
    }

    private NavigationView.OnNavigationItemSelectedListener mSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_events_calendar:
                            menuItem.setChecked(true);
                            break;
                        case R.id.navigation_index_events:
                        case R.id.navigation_join_events:
                            EventActivity.start(CalendarActivity.this);
                            break;
                        case R.id.navigation_search_events:
                            SearchActivity.start(CalendarActivity.this);
                            break;
                        default:
                            break;
                    }
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mCalendarView.setDateSelected(CalendarDay.today(), true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(mSelectedListener);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search_events:
                SearchActivity.start(this);
                return true;
            case R.id.set_today:
                mCalendarView.clearSelection();
                CalendarDay now = CalendarDay.today();
                mCalendarView.setDateSelected(now, true);
                mCalendarView.setCurrentDate(now);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}