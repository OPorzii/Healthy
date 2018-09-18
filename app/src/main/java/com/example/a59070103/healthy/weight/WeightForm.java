package com.example.a59070103.healthy.weight;

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

import com.example.a59070103.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightForm extends Fragment{



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWeightSaveBtn();
        initBackBtn();
    }


    void initBackBtn(){
        Button _backBtn = (Button) getView().findViewById(R.id.back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment())
                        .addToBackStack(null).commit();
            }
        });

    }

    void initWeightSaveBtn(){

        Button _addweighBtn = (Button) getView().findViewById(R.id.weight_save_btn);
        _addweighBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText _addWeight = (EditText) getView().findViewById(R.id.add_weight);

                if(_addWeight.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Fill date or weight", Toast.LENGTH_SHORT).show();
                } else {
                    String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    Float _Weight = Float.parseFloat(_addWeight.getText().toString());
                    addWeight(new WeightInfo(_Weight, dateNow, ""));
                    Toast.makeText(getActivity(), "Your weight ADDED!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    void addWeight(WeightInfo weight){
        FirebaseFirestore mdb = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mdb.collection("myfitness").document(mAuth.getUid()).collection("weight")
                .document(weight.getDate()).set(weight).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("AddWeightResult", "Success!");
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("AddWeightResult", e.getMessage());

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weight_form, container, false);
    }
}
