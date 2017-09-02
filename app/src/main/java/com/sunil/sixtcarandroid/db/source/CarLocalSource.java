package com.sunil.sixtcarandroid.db.source;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sunil.sixtcarandroid.db.GreenDaoDatabase;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.db.model.CarDao;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by sunil on 31-08-2017.
 */

public class CarLocalSource implements CarSource{

    private final  static String TAG = CarLocalSource.class.getSimpleName();

    private CarDao getCarDao() {
        return GreenDaoDatabase.getInstance().getDaoSession().getCarDao();
    }

    @Override
    public Flowable<List<Car>> getCars() {
        return Flowable.fromCallable(new Callable<List<Car>>() {
            @Override
            public List<Car> call() throws Exception {
                List<Car> list = getCarDao().loadAll();
                Log.d(TAG, "getKickStarters: " + list.size());
                return list;
            }
        });
    }

    @Override
    public Observable<Car> getCarById(final String carId) {
        return Observable.fromCallable(new Callable<Car>() {
            @Override
            public Car call() throws Exception {
               Car car  = getCarDao().queryBuilder()
                        .where(CarDao.Properties.Car_id.eq(carId)).unique();
                return car;
            }

        });
    }

    @Override
    public void saveCar(@NonNull Car car) {
        getCarDao().insertOrReplace(car);
    }

    @Override
    public void deleteCar(@NonNull String id) {
        getCarDao().deleteByKey(Long.valueOf(id));
    }

    @Override
    public long getCarCount() {
        return getCarDao().count();
    }

    @Override
    public Flowable<List<Car>> getKCarrByPercentage(int percetage, int backers) {
        return null;
    }
}
