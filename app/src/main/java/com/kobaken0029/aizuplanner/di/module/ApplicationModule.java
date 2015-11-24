package com.kobaken0029.aizuplanner.di.module;

import android.content.Context;

import com.kobaken0029.aizuplanner.AizuPlannerApplication;
import com.kobaken0029.aizuplanner.helper.ToolbarHelper;
import com.kobaken0029.aizuplanner.helper.WebApiHelper;
import com.kobaken0029.aizuplanner.helper.impl.ToolbarHelperImpl;
import com.kobaken0029.aizuplanner.helper.impl.WebApiHelperImpl;
import com.kobaken0029.aizuplanner.view.controller.CalendarController;
import com.kobaken0029.aizuplanner.view.controller.EventController;
import com.kobaken0029.aizuplanner.view.controller.NavigationController;
import com.kobaken0029.aizuplanner.view.controller.impl.CalendarControllerImpl;
import com.kobaken0029.aizuplanner.view.controller.impl.EventControllerImpl;
import com.kobaken0029.aizuplanner.view.controller.impl.NavigationControllerImpl;

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

    @Provides
    @Singleton
    NavigationController provideNavigationController() {
        return new NavigationControllerImpl();
    }

    @Provides
    @Singleton
    CalendarController provideCalendarController() {
        return new CalendarControllerImpl();
    }

    @Provides
    @Singleton
    EventController provideEventController() {
        return new EventControllerImpl();
    }
}
