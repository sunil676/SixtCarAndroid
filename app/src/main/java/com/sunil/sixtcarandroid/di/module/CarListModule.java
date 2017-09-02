package com.sunil.sixtcarandroid.di.module;

import com.sunil.sixtcarandroid.ui.carlist.CarListContract;
import com.sunil.sixtcarandroid.ui.carlist.CarListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunil on 02-09-2017.
 */
@Module
public class CarListModule {
    @Provides
    CarListContract.Presenter getCarPresenter() {
        return new CarListPresenter();
    }
}
