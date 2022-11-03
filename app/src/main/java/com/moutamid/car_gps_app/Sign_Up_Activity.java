package com.moutamid.car_gps_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Sign_Up_Activity extends AppCompatActivity {

    Button sign_btn;
    LinearLayout goto_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_btn = findViewById(R.id.register_btn);
        goto_signin = findViewById(R.id.goto_signin);

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_Up_Activity.this , MainActivity.class);
                startActivity(intent);
            }
        });

        goto_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_Up_Activity.this , Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}