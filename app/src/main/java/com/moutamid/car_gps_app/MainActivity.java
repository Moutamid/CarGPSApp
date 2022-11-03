package com.moutamid.car_gps_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.moutamid.fragment.home_fragment;
import com.moutamid.fragment.position_fragment;
import com.moutamid.fragment.report_fragment;
import com.moutamid.fragment.settings_fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView title;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        title = toolbar.findViewById(R.id.title);
        title.setText("Dashboard");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new home_fragment()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.home){
            title.setText("Dashboard");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new home_fragment()).commit();
        }
        else if(id==R.id.position){
            title.setText("Position");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new position_fragment()).commit();

        }
        else if(id==R.id.history){
            title.setText("History");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new position_fragment()).commit();

        }
        else if(id==R.id.vehicle){
            title.setText("Group Vehicle");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();

        }
        else if(id==R.id.report){
            title.setText("Reports");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new report_fragment()).commit();

        }
        else if(id==R.id.alarms){
            title.setText("Alarms");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();

        }
        else if(id==R.id.notiification){
            title.setText("Notifications");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();

        }
        else if(id==R.id.help){
            title.setText("Help");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();
        }
        else if(id==R.id.logout){
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.settings){
            title.setText("Maintenance");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();

        }
        drawer.closeDrawers();
        return false;
    }
}