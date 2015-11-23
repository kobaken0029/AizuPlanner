package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;
import com.kobaken0029.aizuplanner.view.fragment.EventListFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends BaseActivity {
    public static final String TAG = CalendarActivity.class.getSimpleName();

    private ActionBarDrawerToggle mDrawerToggle;
    private MyEventRecyclerViewAdapter mRecyclerViewAdapter;

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

    @OnClick(R.id.fab)
    void onClickFab() {
        EventActivity.start(CalendarActivity.this, mCalendarView.getCurrentDate());
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, CalendarActivity.class));
    }

    private OnDateSelectedListener mDateSelectedListener =
            new OnDateSelectedListener() {
                @Override
                public void onDateSelected(
                        @NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    mCalendarView.setCurrentDate(date);
                    List<DummyContent.DummyItem> items = DummyContent.ITEMS;
                    if (items.size() > 0) {
                        items.remove(0);
                    } else {
                        items.add(new DummyContent.DummyItem(String.valueOf(items.size()), "Item " + items.size(), ""));
                    }
                    mRecyclerViewAdapter.setValues(items);
                }
            };

    private EventListFragment.OnListFragmentInteractionListener mListInteractionListener =
            new EventListFragment.OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(DummyContent.DummyItem item) {
                    EventActivity.start(CalendarActivity.this, item);
                }
            };

    private NavigationView.OnNavigationItemSelectedListener mNavigationSelectedListener =
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
        mToolbarHelper.init(this, mToolbar, R.string.app_name, false);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(mNavigationSelectedListener);

        mCalendarView.setDateSelected(CalendarDay.today(), true);
        mCalendarView.setOnDateChangedListener(mDateSelectedListener);

        mRecyclerViewAdapter = new MyEventRecyclerViewAdapter(DummyContent.ITEMS, mListInteractionListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
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

    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public void setToday() {
        mCalendarView.clearSelection();
        CalendarDay now = CalendarDay.today();
        mCalendarView.setDateSelected(now, true);
        mCalendarView.setCurrentDate(now);
    }
}
