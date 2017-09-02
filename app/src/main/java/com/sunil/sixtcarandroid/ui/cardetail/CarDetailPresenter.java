package com.sunil.sixtcarandroid.ui.cardetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.db.source.CarLocalSource;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarDetailPresenter implements CarDetailContract.Presenter{

    @NonNull
    private CarDetailContract.View mCarView;
    private Context context;
    private CompositeDisposable mCompositeDisposable;
    private CarLocalSource carLocalSource;


    public CarDetailPresenter(){
        carLocalSource = new CarLocalSource();
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
    public void attachView(CarDetailContract.View view) {
        mCarView = view;
    }

    @Override
    public void loadCarDetail(String s_n0) {

        Log.v("", "S_SN: "+s_n0);
        Observable<Car> kickStarterObservable = carLocalSource.getCarById(s_n0);
        kickStarterObservable.doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                //  Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        }).subscribe(new Consumer<Car>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Car car) throws Exception {
                mCarView.onKickStartersOk(car);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mCarView.showLoadErrorMessage(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //mKickStartView.showLoadErrorMessage("Error");
            }
        });

    }
}

