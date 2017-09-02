package com.sunil.sixtcarandroid.ui.map;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.db.source.CarLocalSource;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarMapPresenter implements CarMapContract.Presenter{

    @NonNull
    private CarMapContract.View mCarMapView;

    private CompositeDisposable mCompositeDisposable;

    private CarLocalSource mCarLocalDataSource;

    public CarMapPresenter( ) {
        mCarLocalDataSource = new CarLocalSource();
    }

    @Override
    public void subscribe() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void attachView(CarMapContract.View view) {
        mCarMapView= view;
    }


    private void loadFromDb(){
        Flowable<List<Car>> listFlowable = mCarLocalDataSource.getCars();
        listFlowable.doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Subscription subscription) throws Exception {

            }
        }).subscribe(new Consumer<List<Car>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<Car> kickStarters) throws Exception {
                mCarMapView.onCarsOk(kickStarters);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                Preconditions.checkNotNull(throwable);
                mCarMapView.showLoadErrorMessage(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                // mKickStartView.onLoadFinish();
            }
        });
    }

    @Override
    public void loadCarsDb() {
        loadFromDb();
    }
}
