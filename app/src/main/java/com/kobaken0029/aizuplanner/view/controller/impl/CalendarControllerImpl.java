package com.kobaken0029.aizuplanner.view.controller.impl;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.activity.EventActivity;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;
import com.kobaken0029.aizuplanner.view.controller.CalendarController;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;

public class CalendarControllerImpl implements CalendarController {
    @Bind(R.id.calendar_view)
    MaterialCalendarView mCalendarView;
    @Bind(R.id.fab)
    View mFab;

    private Context mContext;
    private MyEventRecyclerViewAdapter mAdapter;

    public void init(Context context, MyEventRecyclerViewAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    public void onCreate() {
        mCalendarView.setDateSelected(CalendarDay.today(), true);
    }

    public void onStart() {
        mCalendarView.setOnDateChangedListener(mDateSelectedListener);
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        EventActivity.start(mContext, mCalendarView.getCurrentDate());
    }

    private OnDateSelectedListener mDateSelectedListener =
            new OnDateSelectedListener() {
                @Override
                public void onDateSelected(
                        @NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    setDay(date);
                }
            };

    @MainThread
    public void setToday() {
        setDay(CalendarDay.today());
    }

    @MainThread
    private void setDay(CalendarDay day) {
        mCalendarView.clearSelection();
        mCalendarView.setDateSelected(day, true);
        mCalendarView.setCurrentDate(day);
        mAdapter.setValues(Arrays.asList(DummyContent.ITEM_MAP.get(day.getDay())));
    }
}
