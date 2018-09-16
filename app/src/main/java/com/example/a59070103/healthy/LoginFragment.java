package com.example.a59070103.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new MenuFragment())
                    .addToBackStack(null)
                    .commit();
        }
        initLoginBtn();
        initRegisterBtn();


    }

    void initLoginBtn(){
        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _userpasswordStr = _password.getText().toString();

                if(_userIdStr.isEmpty() || _userpasswordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(_userIdStr, _userpasswordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("LoginResult","Login With "+mAuth.getCurrentUser().getEmail());
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_view, new MenuFragment())
                                        .addToBackStack(null)
                                        .commit();
                            } else {
                                Toast.makeText(
                                        getActivity(), "กรุณายืนยันอีเมลก่อนเข้าใช้งาน", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_view, new LoginFragment())
                                        .addToBackStack(null)
                                        .commit();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }








    void initRegisterBtn(){
        TextView _registerBtn = (TextView) getView().findViewById(R.id.register_link);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}
