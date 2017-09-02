package com.sunil.sixtcarandroid.ui.cardetail;

import com.sunil.sixtcarandroid.base.BaseContract;
import com.sunil.sixtcarandroid.db.model.Car;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarDetailContract {
    public interface Presenter extends BaseContract.Presenter<CarDetailContract.View> {
        void loadCarDetail(String car_no);
    }

    public interface View extends BaseContract.View{
        void onKickStartersOk(Car car);
        void showLoadErrorMessage(String errorMsg);
    }

}

