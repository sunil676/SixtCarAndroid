package com.sunil.sixtcarandroid.di.component;

import com.sunil.sixtcarandroid.di.module.CarDetailModule;
import com.sunil.sixtcarandroid.di.scope.PerActivity;
import com.sunil.sixtcarandroid.ui.cardetail.CarDetailFragment;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sunil on 02-09-2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CarDetailModule.class)
public interface CarDetailComponent {
    void inject(CarDetailFragment carDetailFragment);
}

