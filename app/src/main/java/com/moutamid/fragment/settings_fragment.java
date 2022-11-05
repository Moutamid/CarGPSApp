package com.moutamid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moutamid.car_gps_app.R;

public class settings_fragment extends Fragment {

    private CardView aboutCard,infoCard,locationCard,notificationCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragment,container,false);
        aboutCard = view.findViewById(R.id.card1);
        infoCard = view.findViewById(R.id.card4);
        locationCard = view.findViewById(R.id.card2);
        notificationCard = view.findViewById(R.id.card3);
        notificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new notification_fragment()).commit();
            }
        });
        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new VehicleGroupFragment()).commit();

            }
        });
        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InfoFragment()).commit();
            }
        });
        aboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InfoFragment()).commit();
            }
        });
        return view;
    }
}
