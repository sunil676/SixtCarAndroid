package com.sunil.sixtcarandroid.base;

/**
 * Created by sunil on 31-08-2017.
 */

public class BaseContract {

    public interface Presenter<T>{
        void subscribe();
        void unSubscribe();
        void attachView(T view);
    }

    public interface View {

    }
}
