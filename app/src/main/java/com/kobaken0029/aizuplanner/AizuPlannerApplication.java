package com.kobaken0029.aizuplanner;

import android.app.Application;

import com.kobaken0029.aizuplanner.di.component.ApplicationComponent;
import com.kobaken0029.aizuplanner.di.component.DaggerApplicationComponent;
import com.kobaken0029.aizuplanner.di.module.ApplicationModule;

public class AizuPlannerApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
