package com.kobaken0029.aizuplanner.di.component;

import android.content.Context;

import com.kobaken0029.aizuplanner.di.module.ApplicationModule;
import com.kobaken0029.aizuplanner.view.activity.BaseActivity;
import com.kobaken0029.aizuplanner.view.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);

    Context context();
}
