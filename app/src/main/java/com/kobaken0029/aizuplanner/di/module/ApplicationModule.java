package com.kobaken0029.aizuplanner.di.module;

import android.content.Context;

import com.kobaken0029.aizuplanner.AizuPlannerApplication;
import com.kobaken0029.aizuplanner.helper.ToolbarHelper;
import com.kobaken0029.aizuplanner.helper.WebApiHelper;
import com.kobaken0029.aizuplanner.helper.impl.ToolbarHelperImpl;
import com.kobaken0029.aizuplanner.helper.impl.WebApiHelperImpl;

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

    @Provides
    @Singleton
    ToolbarHelper provideToolbarHelper() {
        return new ToolbarHelperImpl();
    }

    @Provides
    @Singleton
    WebApiHelper provideWebApiHelper() {
        return new WebApiHelperImpl();
    }
}
