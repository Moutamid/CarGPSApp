package com.moutamid.car_gps_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.HashMap;

public class AlarmDetails extends AppCompatActivity {

    private CarDetails model;
    private TextView carnameTxt;
    private EditText speedTxt;
    private Button saveBtn;
    private DatabaseReference db;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_details);
        model = getIntent().getParcelableExtra("carDetail");
        carnameTxt = findViewById(R.id.car);
        speedTxt = findViewById(R.id.speed);
        saveBtn = findViewById(R.id.save);
        db = FirebaseDatabase.getInstance().getReference().child("Car");
        carnameTxt.setText(model.getCar());
        speedTxt.setText(model.getSpeed());
        backImg = findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlarmDetails.this,MainActivity.class));
                finish();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("speed",speedTxt.getText().toString());
                db.child(model.getId()).updateChildren(hashMap);
            }
        });
    }
}