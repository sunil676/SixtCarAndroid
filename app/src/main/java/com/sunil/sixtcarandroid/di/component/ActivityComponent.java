package com.sunil.sixtcarandroid.di.component;

import com.sunil.sixtcarandroid.MainActivity;
import com.sunil.sixtcarandroid.di.module.ActivityModule;
import com.sunil.sixtcarandroid.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by sunil on 02-09-2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}