package com.sunil.sixtcarandroid.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sunil.sixtcarandroid.MainApplication;
import com.sunil.sixtcarandroid.R;
import com.sunil.sixtcarandroid.db.model.Car;
import com.sunil.sixtcarandroid.di.component.ApplicationComponent;
import com.sunil.sixtcarandroid.di.component.CarMapComponent;
import com.sunil.sixtcarandroid.di.component.DaggerCarMapComponent;
import com.sunil.sixtcarandroid.di.module.CarMapModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarsMapFragment extends Fragment implements CarMapContract.View {

    @Inject
    CarMapContract.Presenter mPresenter;
    @BindView(R.id.mapView)
    MapView mapView;
    GoogleMap mGoogleMap;

    public static CarsMapFragment newInstance() {
        return new CarsMapFragment();
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
        CarMapComponent carMapComponent = DaggerCarMapComponent.builder()
                .applicationComponent(applicationComponent)
                .carMapModule(new CarMapModule())
                .build();
        carMapComponent.inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cars_map, container, false);
        ButterKnife.bind(this, root);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // needed to get the map to display immediately

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                // Need to call MapsInitializer before doing any CameraUpdateFactory calls
                mGoogleMap = googleMap;
                MapsInitializer.initialize(getActivity());
                mPresenter.loadCarsDb();
            }
        });


        return root;
    }


    @Override
    public void onCarsOk(List<Car> cars) {

        final LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int i = 0; i < cars.size(); i++) {
            final LatLng position = new LatLng(cars.get(i).getLatitude(), cars.get(i).getLongitude());
            final MarkerOptions options = new MarkerOptions().position(position).title(cars.get(i).getName());
                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_car_demo));
            mGoogleMap.addMarker(options);
            builder.include(position);
        }
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(cars.get(0).getLatitude(), cars.get(0).getLongitude()));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);

        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom);

    }

    @Override
    public void showLoadErrorMessage(String errorMsg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
