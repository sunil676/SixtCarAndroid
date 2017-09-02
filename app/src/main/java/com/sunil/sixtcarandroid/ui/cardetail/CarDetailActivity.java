package com.sunil.sixtcarandroid.ui.cardetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sunil.sixtcarandroid.R;
import com.sunil.sixtcarandroid.util.ActivityUtil;

import butterknife.ButterKnife;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarDetailActivity extends AppCompatActivity {

    private String s_n0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            s_n0 = bundle.getString("car_n0");
        }

        if (savedInstanceState == null){
            // create the fragment here
            ActivityUtil.addFragmentToActivity(getFragmentManager(), CarDetailFragment.newInstance(s_n0), R.id.frame_content_detail, "CarDetailFragment");
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}