package com.kobaken0029.aizuplanner.view.controller;

import android.content.Context;

import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;

public interface CalendarController {
    void init(Context context, MyEventRecyclerViewAdapter adapter);
    void onStart();
    void setToday();
}
