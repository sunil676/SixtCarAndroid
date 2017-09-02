package com.sunil.sixtcarandroid.di.module;

import android.app.Application;

import com.sunil.sixtcarandroid.MainApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunil on 31-08-2017.
 */
@Module
public class ApplicationModule {

    private final MainApplication mainApplication;

    public ApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    public Application provideApplication() {
        return mainApplication;
    }

}
