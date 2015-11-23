package com.kobaken0029.aizuplanner.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kobaken0029.aizuplanner.AizuPlannerApplication;
import com.kobaken0029.aizuplanner.di.component.ApplicationComponent;
import com.kobaken0029.aizuplanner.di.module.ActivityModule;
import com.kobaken0029.aizuplanner.helper.ToolbarHelper;
import com.kobaken0029.aizuplanner.helper.WebApiHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @Inject
    ToolbarHelper mToolbarHelper;
    @Inject
    WebApiHelper mWebApiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getApplicationComponent().inject(this);
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    public void addFragment(int containerViewId, Fragment fragment, String key) {
        getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment, key)
                .commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment, String key) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, fragment, key)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AizuPlannerApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
