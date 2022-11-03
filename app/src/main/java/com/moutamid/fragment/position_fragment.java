package com.moutamid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.adapters.PositionListAdapter;
import com.moutamid.car_gps_app.model.Position;

import java.util.ArrayList;

public class position_fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Position> positionArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.position_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        positionArrayList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Position position = new Position();
            position.setName("01 logan 29727-A-45");
            position.setPosition("OH 59min");
            position.setCity("Route sans nom, Tamassint, Morocco");
            position.setTime("03/11/2022 11:36:14");
            positionArrayList.add(position);
        }

        PositionListAdapter adapter = new
                PositionListAdapter(getActivity(),positionArrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
