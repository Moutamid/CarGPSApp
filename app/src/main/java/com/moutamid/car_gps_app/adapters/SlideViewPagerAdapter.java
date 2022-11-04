package com.moutamid.car_gps_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.listener.LoopingPagerAdapter;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.ArrayList;
import java.util.List;


public class SlideViewPagerAdapter extends PagerAdapter implements LoopingPagerAdapter {

    Context ctx;
    ArrayList<CarDetails> modelDataArrayList;

    public SlideViewPagerAdapter(Context ctx, ArrayList<CarDetails> modelDataArrayList) {
        this.ctx = ctx;
        this.modelDataArrayList = modelDataArrayList;
    }

    @Override
    public int getCount() {
        return modelDataArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider_layout,container,false);
        TextView distance1 =view.findViewById(R.id.distance);
        TextView distance2 =view.findViewById(R.id.dist);
        TextView time =view.findViewById(R.id.time);
        TextView speed =view.findViewById(R.id.speed);
        TextView consumption =view.findViewById(R.id.consumption);

        CarDetails modelData = modelDataArrayList.get(position);

        distance1.setText(modelData.getDistance());
        distance2.setText(modelData.getDistance());
        time.setText(modelData.getTime());
        speed.setText(modelData.getSpeed());
        consumption.setText(modelData.getConsumption());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getRealCount() {
        return modelDataArrayList.size();
    }
}
