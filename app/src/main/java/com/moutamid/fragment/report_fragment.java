package com.moutamid.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Repo;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.databinding.ReportFragmentBinding;
import com.moutamid.car_gps_app.model.Report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class report_fragment extends Fragment {

    private ReportFragmentBinding b;
    private DatabaseReference db;
    private FirebaseUser user;
    private String vehicle = "";
    private String report = "";
    private String sdate = "";
    private String edate = "";
    private String[] reportArray = {"Consumption Report","Summary Report","Speed Report",
            "Temperature","Probe Report","Driver Behaviour","Temperature Frigo"};
    private String[] vehicleArray = {"All Vehicle","01 logan 29727-A-45","02 renault clio 29723-A-45",
            "03 clio auto 29686-A-45","04 Cilo 29722-A-45","05 Cilo 29724-A-45","06 Accent 29836-A-45"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        b = ReportFragmentBinding.inflate(getLayoutInflater());
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference().child("Reports");
        b.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                report = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vehicle = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        sdate = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                        b.start.setText(sdate);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        b.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edate = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                        b.end.setText(edate);
                    }
                }, yy, mm, dd);
                datePicker.show();

            }
        });

        b.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(report) && !TextUtils.isEmpty(vehicle) && !TextUtils.isEmpty(sdate)
                        && !TextUtils.isEmpty(edate)) {
                    sendReport();
                }else {
                    Toast.makeText(getActivity(),"Please fill the form!",Toast.LENGTH_LONG).show();
                }
            }
        });

        getReportData();

        return b.getRoot();
    }

    private void getReportData() {
        db.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Report report = snapshot.getValue(Report.class);
                            b.start.setText(report.getStart_date());
                            b.end.setText(report.getEnd_date());

                            for (int i = 0 ; i < reportArray.length; i++){
                                if (reportArray[i].equals(report.getReport_name())){
                                    b.spinner1.setSelection(i);
                                }
                            }

                            for (int i = 0 ; i < vehicleArray.length; i++){
                                if (vehicleArray[i].equals(report.getVehicle())){
                                    b.spinner2.setSelection(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void sendReport() {
        //String key = db.push().getKey();
        //Report model = new Report(user.getUid(),report,vehicle,sdate,edate);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",user.getUid());
        hashMap.put("report_name",report);
        hashMap.put("vehicle",vehicle);
        hashMap.put("start_date",sdate);
        hashMap.put("end_date",edate);
        db.child(user.getUid()).updateChildren(hashMap);
        Toast.makeText(getActivity(),"Report Sent Successfully!",Toast.LENGTH_LONG).show();
    }
}
