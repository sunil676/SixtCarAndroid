package com.sunil.sixtcarandroid.di.component;

import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.di.module.CarListModule;
import com.sunil.sixtcarandroid.di.scope.PerActivity;
import com.sunil.sixtcarandroid.ui.carlist.CarListFragment;

import dagger.Component;

/**
 * Created by sunil on 02-09-2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CarListModule.class)
public interface CarListComponent {
    void inject(CarListFragment carListFragment);
}

