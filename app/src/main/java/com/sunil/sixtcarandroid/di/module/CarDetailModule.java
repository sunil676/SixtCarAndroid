package com.sunil.sixtcarandroid.di.module;

import com.sunil.sixtcarandroid.ui.cardetail.CarDetailContract;
import com.sunil.sixtcarandroid.ui.cardetail.CarDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunil on 02-09-2017.
 */

@Module
public class CarDetailModule {
    @Provides
    CarDetailContract.Presenter getCarDetailPresenter() {
        return new CarDetailPresenter();
    }
}
