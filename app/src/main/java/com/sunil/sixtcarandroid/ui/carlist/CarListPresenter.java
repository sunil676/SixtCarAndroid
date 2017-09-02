package com.sunil.sixtcarandroid.ui.carlist;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.sunil.sixtcarandroid.api.APIService;
import com.sunil.sixtcarandroid.api.apimodel.CarApiModel;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.db.source.CarLocalSource;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarListPresenter implements CarListContract.Presenter{

    @NonNull
    private CarListContract.View mCarListView;

    private CompositeDisposable mCompositeDisposable;

    private CarLocalSource mCarLocalDataSource;

    public CarListPresenter( ) {
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
    public void attachView(CarListContract.View view) {
        mCarListView= view;
    }


    @Override
    public void loadCarsAPI() {
        mCarListView.showProgress(true);
        final Flowable<List<CarApiModel>> listFlowable = APIService.Creator.newApiClient().getCars();
        listFlowable .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Subscription subscription) throws Exception {

            }
        }).subscribe(new Consumer<List<CarApiModel>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<CarApiModel> carApiModels) throws Exception {
                // save into db
                for (int index =0; index < carApiModels.size(); index++) {
                    CarApiModel model = carApiModels.get(index);
                    Car car = new Car();
                    car.setCar_id(model.getId());
                    car.setModelIdentifier(model.getModelIdentifier());
                    car.setModelName(model.getModelName());
                    car.setName(model.getName());
                    car.setMake(model.getMake());
                    car.setGroup(model.getGroup());
                    car.setColor(model.getColor());
                    car.setSeries(model.getSeries());
                    car.setFuelType(model.getFuelType());
                    car.setFuelLevel(model.getFuelLevel());
                    car.setTransmission(model.getTransmission());
                    car.setLicensePlate(model.getLicensePlate());
                    car.setLatitude(model.getLatitude());
                    car.setLongitude(model.getLongitude());
                    car.setInnerCleanliness(model.getInnerCleanliness());
                    car.setCarImageUrl(model.getCarImageUrl());
                    mCarLocalDataSource.saveCar(car);
                }
                mCarListView.hideProgress();
                loadFromDb();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                Preconditions.checkNotNull(throwable);
                mCarListView.showLoadErrorMessage(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                // mCarListView.onLoadFinish();
            }
        });

    }

    @Override
    public void loadCarsDb() {
        loadFromDb();
    }

    @Override
    public void loadDetailCar(boolean isDetail) {

    }

    @Override
    public long getCountDb() {
        return mCarLocalDataSource.getCarCount();
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
                mCarListView.onCarsOk(kickStarters);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                Preconditions.checkNotNull(throwable);
                mCarListView.showLoadErrorMessage(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                // mKickStartView.onLoadFinish();
            }
        });
    }
}
