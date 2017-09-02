package com.sunil.sixtcarandroid.di.component;

import android.app.Application;

import com.sunil.sixtcarandroid.MainApplication;
import com.sunil.sixtcarandroid.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sunil on 31-08-2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainApplication mainApplication);

    Application getApplication();

}