package com.sunil.sixtcarandroid.di.component;

import com.sunil.sixtcarandroid.di.module.CarMapModule;
import com.sunil.sixtcarandroid.di.scope.PerActivity;
import com.sunil.sixtcarandroid.ui.map.CarsMapFragment;

import dagger.Component;

/**
 * Created by sunil on 02-09-2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CarMapModule.class)
public interface CarMapComponent {
    void inject(CarsMapFragment carsMapFragment);
}



