package com.example.a59070103.healthy;

import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Button _btnCal = (Button) getView().findViewById(R.id.btnCal);
        _btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView bmitxt = (TextView) getView().findViewById(R.id.ResultBmi);
                EditText _weight = (EditText) getView().findViewById(R.id.txt_weight);
                EditText _height = (EditText) getView().findViewById(R.id.txt_height);
                if(_weight.getText().toString().isEmpty() || _height.getText().toString().isEmpty()){
                    Toast.makeText(
                            getActivity(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                } else {
                    Float weight = Float.parseFloat(_weight.getText().toString());
                    Float height = Float.parseFloat(_height.getText().toString());
                    String res = String.format("BMI = %.2f", weight/((height/100)*(height/100)));
                    bmitxt.setText(res);
                }






            }
        });

    }

}
