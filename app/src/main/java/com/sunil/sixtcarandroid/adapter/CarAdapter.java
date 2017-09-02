package com.sunil.sixtcarandroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sunil.sixtcarandroid.R;
import com.sunil.sixtcarandroid.db.model.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 02-09-2017.
 */

public class CarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Car> itemModels;
    private Context context;
    private onItemClickListener listener;

    public CarAdapter(Context context, List<Car> kickStarters, Fragment fragment) {
        this.itemModels = kickStarters;
        this.context = context;
        this.listener = (onItemClickListener) fragment;
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Car model = itemModels.get(position);
        initializeViews(model, holder, position);
    }


    private void initializeViews(final Car model, final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder)holder).name.setText(model.getName());
        ((ItemViewHolder)holder).modelname.setText("Model Name: "+model.getModelName());
        ((ItemViewHolder)holder).make.setText("Make: "+model.getMake());
        ((ItemViewHolder)holder).fueltype.setText("Fuel Type: "+model.getFuelType()+"");
        ((ItemViewHolder)holder).fuelevel.setText("Fuel Level: "+model.getFuelLevel()+"");
        ((ItemViewHolder)holder).relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemDetailClick(model);
            }
        });
        //https://prod.drive-now-content.com/fileadmin/user_upload_global/assets/cars/mini/midnight_black/2x/car.png
        String imageUrl = "https://prod.drive-now-content.com/fileadmin/user_upload_global/assets/cars/"+model.getModelIdentifier()+"/"+model.getColor()+"/"+"2x/car.png";
        if (imageUrl != null && !imageUrl.isEmpty()){
            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_car_demo)
                    .error(R.drawable.ic_car_demo)
                    .into(((ItemViewHolder)holder).imageView);
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.relative)
        RelativeLayout relative;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener{
        public void itemDetailClick(Car kickStarter);
    }

    public void setSearchResult(List<Car> result) {
        itemModels = result;
        notifyDataSetChanged();
    }
}
