package com.sunil.sixtcarandroid.ui.carlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunil.sixtcarandroid.MainApplication;
import com.sunil.sixtcarandroid.R;
import com.sunil.sixtcarandroid.adapter.CarAdapter;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.di.component.ApplicationComponent;
import com.sunil.sixtcarandroid.di.component.CarListComponent;
import com.sunil.sixtcarandroid.di.component.DaggerCarListComponent;
import com.sunil.sixtcarandroid.di.module.CarListModule;
import com.sunil.sixtcarandroid.ui.cardetail.CarDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarListFragment extends Fragment implements CarListContract.View, CarAdapter.onItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Inject
    CarListContract.Presenter mPresenter;
    private CarAdapter mCarAdapter;
    private List<Car> mListCar;

    public static CarListFragment newInstance() {
        return new CarListFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // do things if you want to create only first time when activity created.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency();
        mPresenter.attachView(this);
    }

    private void injectDependency() {
        ApplicationComponent applicationComponent = ((MainApplication) getActivity().getApplication()).getApplicationComponent();
        CarListComponent carListComponent = DaggerCarListComponent.builder()
                .applicationComponent(applicationComponent)
                .carListModule(new CarListModule())
                .build();
        carListComponent.inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_car_list, container, false);
        ButterKnife.bind(this, root);
        progress.setVisibility(View.GONE);
        if (mPresenter != null) {
            if (mPresenter.getCountDb() > 0) {
                mPresenter.loadCarsDb();
            } else {
                mPresenter.loadCarsAPI();
            }
        }

        return root;
    }
    @Override
    public void onCarsOk(List<Car> cars) {
        mListCar = cars;
        mCarAdapter = new CarAdapter(getActivity(), cars, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mCarAdapter);
    }

    @Override
    public void showProgress(boolean show) {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadErrorMessage(String errorMsg) {
        progress.setVisibility(View.GONE);
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView(boolean isShow) {
        // show error view
    }

    @Override
    public void itemDetailClick(Car car) {
        String  car_no = car.getCar_id();
        Intent intent = new Intent(getActivity(), CarDetailActivity.class);
        intent.putExtra("car_n0", car_no);
        startActivity(intent);
    }
}
