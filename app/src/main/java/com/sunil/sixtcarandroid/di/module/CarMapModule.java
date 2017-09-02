package com.sunil.sixtcarandroid.di.module;

import com.sunil.sixtcarandroid.ui.map.CarMapContract;
import com.sunil.sixtcarandroid.ui.map.CarMapPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunil on 02-09-2017.
 */
@Module
public class CarMapModule {
    @Provides
    CarMapContract.Presenter getCarPresenter() {
        return new CarMapPresenter();
    }
}
