package com.moutamid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.adapters.AllCommandsListAdapter;
import com.moutamid.car_gps_app.adapters.PositionListAdapter;
import com.moutamid.car_gps_app.listener.ItemPressListener;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.ArrayList;

public class all_commands_fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<CarDetails> positionArrayList;
    private DatabaseReference db;
    private EditText searchTxt;
    AllCommandsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.position_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        positionArrayList = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference().child("Car");
        getPosition();
        searchTxt = view.findViewById(R.id.seacrh);
        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    adapter.getFilter().filter(charSequence.toString());
                }else {
                    getPosition();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void getPosition() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    positionArrayList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        CarDetails model = ds.getValue(CarDetails.class);
                        positionArrayList.add(model);
                    }
                    adapter = new
                            AllCommandsListAdapter(getActivity(),positionArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setItemPressListener(new ItemPressListener() {
                        @Override
                        public void onItemClick(int position, View view) {
                            Toast.makeText(getActivity(), positionArrayList.get(position).getCar(), Toast.LENGTH_SHORT).show();
                            showCommandsDialog(positionArrayList.get(position));
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

    private void showCommandsDialog(CarDetails details) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View add_view = inflater.inflate(R.layout.commands_dialog_box,null);
        TextView carName = add_view.findViewById(R.id.carName);
        LinearLayout linearLayout1 = add_view.findViewById(R.id.turn_on);
        LinearLayout linearLayout2 = add_view.findViewById(R.id.turn_off);
        LinearLayout linearLayout3 = add_view.findViewById(R.id.flash);
        LinearLayout linearLayout4 = add_view.findViewById(R.id.unblock);
        LinearLayout linearLayout5 = add_view.findViewById(R.id.honk);
        LinearLayout linearLayout6 = add_view.findViewById(R.id.sms);
        carName.setText(details.getCar());
        builder.setView(add_view);
        AlertDialog alertDialog = builder.create();
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk setdigout 0");
                alertDialog.dismiss();
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk setdigout 1");
                alertDialog.dismiss();
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk cpureset");
                alertDialog.dismiss();
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk flush 354017111490187,internet1.meditel.ma,MEDINET,MEDINET,139.99.253.44,5027,0");
                alertDialog.dismiss();
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk setdigout 01 0 2");
                alertDialog.dismiss();
            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentTxtMessage(details.getPhone(),"lex trk ggps");
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void sentTxtMessage(String phone, String msg) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", phone);
        smsIntent.putExtra("sms_body",msg);
        startActivity(smsIntent);
    }
}
