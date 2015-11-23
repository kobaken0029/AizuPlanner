package com.kobaken0029.aizuplanner.di.module;

import android.content.Context;

import com.kobaken0029.aizuplanner.AizuPlannerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AizuPlannerApplication application;

    public ApplicationModule(AizuPlannerApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application.getApplicationContext();
    }
}
