package com.example.a59070103.healthy;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RegisBtn();
    }


    void RegisBtn() {
        Button _regisBtn = (Button) getView().findViewById(R.id.regis_btn);
        _regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _email = (EditText) getView().findViewById(R.id.inp_email);
                EditText _password = (EditText) getView().findViewById(R.id.inp_pass);
                EditText _repassword = (EditText) getView().findViewById(R.id.inp_repass);
                String _emailStr = _email.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _repasswordStr = _repassword.getText().toString();
                if (_emailStr.isEmpty() || _passwordStr.isEmpty() || _repasswordStr.isEmpty()) {
                    Toast.makeText(
                            getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else {
                    if (_passwordStr.length() < 6) {
                        Toast.makeText(
                                getActivity(), "รหัสผ่านสั้นเกิน", Toast.LENGTH_SHORT).show();
                    } else if (!_passwordStr.equals(_repasswordStr)) {
                        Toast.makeText(
                                getActivity(), "รหัสผ่านไม่ตรงกัน", Toast.LENGTH_SHORT).show();

                    } else {
                        createUser(_emailStr, _passwordStr);
                    }

                }

            }
        });
    }

    void createUser(String email, String pass) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("RegisterResult","Success");
                sendVerifiedEmail(mAuth.getCurrentUser());
                mAuth.signOut();
                Toast.makeText(
                        getActivity(), "ลงทะเบียนเรียบร้อย โปรดยืนยัน Email", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(
                        getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RegisterResult",e.getMessage());

            }
        });

    }

    void sendVerifiedEmail(FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("EmailSendResult", "Success!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(
                        getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("EmailSendResult",e.getMessage());
            }
        });
    }
}

