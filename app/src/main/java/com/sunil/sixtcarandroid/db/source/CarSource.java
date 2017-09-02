package com.sunil.sixtcarandroid.db.source;

import android.support.annotation.NonNull;

import com.sunil.sixtcarandroid.db.model.Car;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by sunil on 31-08-2017.
 */

public interface CarSource {

    Flowable<List<Car>> getCars();

    Observable<Car> getCarById(String carId);

    void saveCar(@NonNull Car kickStarter);

    void deleteCar(@NonNull String id);

    long getCarCount();

    Flowable<List<Car>> getKCarrByPercentage(int percetage, int backers);
}
