package com.moutamid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.adapters.PositionListAdapter;
import com.moutamid.car_gps_app.adapters.SlideViewPagerAdapter;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.ArrayList;

public class home_fragment extends Fragment {

    private ViewPager pager;
    private ImageView nextImg,prevImg;
    private SlideViewPagerAdapter adapter;
    private int currentPage = 0;
    private ArrayList<CarDetails> positionArrayList;
    private DatabaseReference db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.swipe_layout, container, false);
        pager = view.findViewById(R.id.viewPager);
        nextImg = view.findViewById(R.id.right_arrow);
        prevImg = view.findViewById(R.id.left_arrow);
        positionArrayList = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference().child("Car");
        getPosition();
        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
            }
        });

        prevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage--;
            }
        });
        return view;
    }

    private void getPosition() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()){
                        CarDetails model = ds.getValue(CarDetails.class);
                        positionArrayList.add(model);
                    }
                    SlideViewPagerAdapter adapter = new
                            SlideViewPagerAdapter(getActivity(),positionArrayList);
                    pager.setAdapter(adapter);
                    pager.setCurrentItem(currentPage);
                    pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
