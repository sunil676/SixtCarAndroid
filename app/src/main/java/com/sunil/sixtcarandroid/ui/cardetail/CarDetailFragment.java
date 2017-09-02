package com.sunil.sixtcarandroid.ui.cardetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sunil.sixtcarandroid.MainApplication;
import com.sunil.sixtcarandroid.R;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.di.component.ApplicationComponent;
import com.sunil.sixtcarandroid.di.component.CarDetailComponent;
import com.sunil.sixtcarandroid.di.component.DaggerCarDetailComponent;
import com.sunil.sixtcarandroid.di.module.CarDetailModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarDetailFragment extends Fragment implements CarDetailContract.View{

    public static final String Tag = CarDetailFragment.class.getSimpleName();
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.modelname)
    TextView modelname;
    @BindView(R.id.make)
    TextView make;
    @BindView(R.id.fueltype)
    TextView fueltype;
    @BindView(R.id.fuelevel)
    TextView fuelevel;
    @BindView(R.id.info)
    RelativeLayout info;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;


    private String url;
    private String title;

    @Inject
    CarDetailContract.Presenter mPresenter;

    public static CarDetailFragment newInstance(String s_no) {
        CarDetailFragment carDetailFragment = new CarDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("s_no", s_no);
        carDetailFragment.setArguments(bundle);
        return carDetailFragment;
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
        CarDetailComponent kickStarterDetailComponent = DaggerCarDetailComponent.builder()
                .applicationComponent(applicationComponent)
                .carDetailModule(new CarDetailModule())
                .build();
        kickStarterDetailComponent.inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_car_detail, container, false);
        ButterKnife.bind(this, root);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String s_n0 = bundle.getString("s_no");
            mPresenter.loadCarDetail(s_n0);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onKickStartersOk(Car car) {
        String imageUrl = "https://prod.drive-now-content.com/fileadmin/user_upload_global/assets/cars/"+car.getModelIdentifier()+"/"+car.getColor()+"/"+"2x/car.png";
        title = car.getName();
        collapsingToolbar.setTitle(title);
        name.setText(car.getName());
        modelname.setText("Model: "+car.getModelName());
        make.setText("Make: "+car.getMake());
        fueltype.setText("Fuel Type: "+car.getFuelType());
        fuelevel.setText("Fuel Level: "+car.getFuelLevel() + "");

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(getActivity())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_car_demo)
                    .error(R.drawable.ic_car_demo)
                    .into(backdrop);
        }

    }

    @Override
    public void showLoadErrorMessage(String errorMsg) {

    }
}
