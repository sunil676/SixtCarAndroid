package com.sunil.sixtcarandroid.ui.map;

import com.sunil.sixtcarandroid.base.BaseContract;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.ui.carlist.CarListContract;

import java.util.List;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarMapContract {
    public interface Presenter extends BaseContract.Presenter<CarMapContract.View> {
        void loadCarsDb();
    }

    public interface View extends BaseContract.View{
        void onCarsOk(List<Car> kickStarters);
        void showLoadErrorMessage(String errorMsg);
    }

}
