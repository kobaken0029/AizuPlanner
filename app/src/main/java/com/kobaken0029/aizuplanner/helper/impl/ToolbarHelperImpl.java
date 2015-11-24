package com.kobaken0029.aizuplanner.helper.impl;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.helper.ToolbarHelper;
import com.kobaken0029.aizuplanner.view.activity.BaseActivity;
import com.kobaken0029.aizuplanner.view.activity.CalendarActivity;
import com.kobaken0029.aizuplanner.view.activity.EventActivity;
import com.kobaken0029.aizuplanner.view.activity.SearchActivity;

public class ToolbarHelperImpl implements ToolbarHelper {
    /**
     * ツールバーを設定する。
     */
    public void init(final BaseActivity activity, Toolbar toolbar, int titleId,
                     Toolbar.OnMenuItemClickListener clickListener) {
        toolbar.setTitle(titleId);
        toolbar.setTitleTextColor(activity.getResources().getColor(android.R.color.white));
        if (isCalendarActivity(activity)) {
            toolbar.inflateMenu(R.menu.menu_calendar);
            toolbar.setNavigationIcon(R.drawable.ic_action_image_dehaze);
        } else if (isEventActivity(activity)) {
            toolbar.inflateMenu(R.menu.menu_event);
            toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        } else if (isSearchActivity(activity)) {
            toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
        if (clickListener != null) {
            toolbar.setOnMenuItemClickListener(clickListener);
        }
    }

    private boolean isCalendarActivity(Activity activity) {
        return activity instanceof CalendarActivity;
    }

    private boolean isEventActivity(Activity activity) {
        return activity instanceof EventActivity;
    }

    private boolean isSearchActivity(Activity activity) {
        return activity instanceof SearchActivity;
    }
}
