package com.example.a59070103.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070103.healthy.R;
import com.example.a59070103.healthy.weight.WeightForm;
import com.example.a59070103.healthy.weight.WeightInfo;

import java.util.ArrayList;

public class WeightFragment extends Fragment {


    ArrayList<WeightInfo> weightList = new ArrayList<>();


    @Nullable
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // add weight item
        weightList.add(new WeightInfo(63, "01 Jan 2018", "DOWN"));
        weightList.add(new WeightInfo(52, "02 Jan 2018", "UP"));
        weightList.add(new WeightInfo(22, "03 Jan 2018", "UP"));


        // get ListView
        ListView weightShowList = getView().findViewById(R.id.weight_list);

        // new adapter
        WeightAdapter weightAdapter = new WeightAdapter(getActivity(),
                R.layout.weight_form_items, weightList);

        // set adapter to ListView
        weightShowList.setAdapter(weightAdapter);

        initAddweightBtn();


    }


    void initAddweightBtn(){
        Button _addweighBtn = (Button) getView().findViewById(R.id.add_weight_btn);
        _addweighBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightForm())
                            .addToBackStack(null).commit();
            }
        });

    }
    public void addWeight(WeightInfo weight){
        weightList.add(weight);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
