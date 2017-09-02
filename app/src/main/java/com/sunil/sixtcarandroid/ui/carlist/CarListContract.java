package com.sunil.sixtcarandroid.ui.carlist;

import com.sunil.sixtcarandroid.base.BaseContract;
import com.sunil.sixtcarandroid.db.model.Car;

import java.util.List;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarListContract {

    public interface Presenter extends BaseContract.Presenter<View> {
        void loadCarsAPI();
        void loadCarsDb();
        void loadDetailCar(boolean isDetail);
        long getCountDb();

    }

    public interface View extends BaseContract.View{
        void onCarsOk(List<Car> kickStarters);
        void showProgress(boolean show);
        void hideProgress();
        void showLoadErrorMessage(String errorMsg);
        void showEmptyView(boolean isShow);

    }
}
