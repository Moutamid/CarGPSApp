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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.car_gps_app.model.User;
import com.moutamid.fragment.HELP_fragment;
import com.moutamid.fragment.VehicleGroupFragment;
import com.moutamid.fragment.alarm_fragment;
import com.moutamid.fragment.change_fragment;
import com.moutamid.fragment.commands_fragment;
import com.moutamid.fragment.history_fragment;
import com.moutamid.fragment.home_fragment;
import com.moutamid.fragment.notification_fragment;
import com.moutamid.fragment.position_fragment;
import com.moutamid.fragment.report_fragment;
import com.moutamid.fragment.settings_fragment;
import com.moutamid.fragment.subscription_fragments;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView title,username,email;
    DrawerLayout drawer;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String emails = "";

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
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        emails = user.getEmail();
        NavigationView navigationView = findViewById(R.id.nav_view);
        username = navigationView.getHeaderView(0).findViewById(R.id.navNameTv);
        email = navigationView.getHeaderView(0).findViewById(R.id.navEmailTv);
        navigationView.setNavigationItemSelectedListener(this);
        title = toolbar.findViewById(R.id.title);
        title.setText("Dashboard");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new home_fragment()).commit();
        getUserDetails();
    }

    private void getUserDetails() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
        db.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            User model = snapshot.getValue(User.class);
                            username.setText(model.getName());
                            email.setText(model.getEmail());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new history_fragment()).commit();

        }
        else if(id==R.id.vehicle){
            title.setText("Vehicle Group");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new VehicleGroupFragment()).commit();

        }
        else if(id==R.id.report){
            title.setText("Reports");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new report_fragment()).commit();

        }
        else if(id==R.id.commands){
            title.setText("Commands");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new commands_fragment()).commit();

        }
        else if(id==R.id.alarms){
            title.setText("Alarms");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new alarm_fragment()).commit();

        }
        else if(id==R.id.notiification){
            title.setText("Notifications");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new notification_fragment()).commit();

        }
        else if(id==R.id.help){
            title.setText("Help");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HELP_fragment()).commit();
        }
        else if(id==R.id.subscription){
            title.setText("Subscription");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new subscription_fragments()).commit();

        }
        else if(id==R.id.logout){
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,Login_Activity.class));
            finish();
        }
        else if(id==R.id.change){
            title.setText("Changement de Mot de");
            Bundle bundle = new Bundle();
            bundle.putString("email",emails);
            change_fragment changeFragment = new change_fragment();
            changeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,changeFragment).commit();

        }
        else if(id==R.id.settings){
            title.setText("Maintenance");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new settings_fragment()).commit();

        }
        drawer.closeDrawers();
        return false;
    }
}