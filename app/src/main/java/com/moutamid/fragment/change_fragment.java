package com.moutamid.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.car_gps_app.MainActivity;
import com.moutamid.car_gps_app.R;

import java.util.HashMap;

public class change_fragment extends Fragment {

    private EditText oldEditTxt,newEditTxt,confirmEditText;
    private String oldPass,newPass,newCPass;
    private Button saveBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ProgressDialog pd;
    private DatabaseReference db;
    private String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_fragment,container,false);
        oldEditTxt = view.findViewById(R.id.old_pass);
        newEditTxt = view.findViewById(R.id.new_pass);
        confirmEditText = view.findViewById(R.id.new_cpass);
        saveBtn = view.findViewById(R.id.update);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        email = getArguments().getString("email");
        db = FirebaseDatabase.getInstance().getReference().child("Users");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPass = oldEditTxt.getText().toString();
                newPass = newEditTxt.getText().toString();
                newCPass = confirmEditText.getText().toString();
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Updating Password....");
                pd.show();

                if (!TextUtils.isEmpty(oldPass) && !TextUtils.isEmpty(newPass) && !TextUtils.isEmpty(newCPass)){
                    reauthenticate(oldPass,newPass);
                }
            }
        });

        return view;
    }

    private void reauthenticate(String pass, String newPass) {
        AuthCredential credential = EmailAuthProvider.getCredential(email,pass);
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //  Toast.makeText(ResetPassword.this,"Password has been verified!",Toast.LENGTH_LONG).show();
                            user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        pd.dismiss();
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("password", newPass);
                                        db.child(user.getUid()).updateChildren(hashMap);

                                        Intent intent = new Intent(getActivity(),MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        getActivity().finish();

                                    }
                                    else {
                                        pd.dismiss();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            Toast.makeText(getActivity(),"Error Occured!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void updatePassword() {
        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("password", newPass);
                            db.child(user.getUid()).updateChildren(hashMap);

                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
