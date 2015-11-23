package com.kobaken0029.aizuplanner.di.component;

import android.support.v7.app.AppCompatActivity;

import com.kobaken0029.aizuplanner.di.PerActivity;
import com.kobaken0029.aizuplanner.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    AppCompatActivity activity();
}
