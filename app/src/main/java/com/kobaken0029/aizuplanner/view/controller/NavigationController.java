package com.kobaken0029.aizuplanner.view.controller;

import android.app.Activity;
import android.support.v7.app.ActionBarDrawerToggle;

public interface NavigationController {
    void init(Activity activity, ActionBarDrawerToggle toggle);
    void onStart();
}
