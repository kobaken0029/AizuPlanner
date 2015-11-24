package com.kobaken0029.aizuplanner.helper;

import android.support.v7.widget.Toolbar;

import com.kobaken0029.aizuplanner.view.activity.BaseActivity;

public interface ToolbarHelper {
    void init(final BaseActivity activity, Toolbar toolbar, int titleId, Toolbar.OnMenuItemClickListener clickListener);
}
