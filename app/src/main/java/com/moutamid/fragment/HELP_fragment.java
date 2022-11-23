package com.moutamid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moutamid.car_gps_app.R;

public class HELP_fragment extends Fragment {

    private ImageView emailImg,whatsappImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.help_fragment,container,false);
        emailImg = view.findViewById(R.id.email);
        whatsappImg = view.findViewById(R.id.watsap);

        emailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "Hello");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Title goes here");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        whatsappImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "Hello");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Title goes here");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });
        return view;
    }
}
