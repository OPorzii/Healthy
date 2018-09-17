package com.example.a59070103.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070103.healthy.R;
import com.example.a59070103.healthy.weight.WeightForm;
import com.example.a59070103.healthy.weight.WeightInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    FirebaseFirestore mdb = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<WeightInfo> weightList = new ArrayList<>();
    ListView weightShowList;


    @Nullable
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        weightShowList = getView().findViewById(R.id.weight_list);

        getDataFromFireStore();
        initAddweightBtn();


    }

    void getDataFromFireStore(){


        final WeightAdapter weightAdapter = new WeightAdapter(getActivity(),
                R.layout.weight_form_items, weightList);
        weightShowList.setAdapter(weightAdapter);



        mdb.collection("myfitness").document(mAuth.getUid())
                .collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    WeightInfo obj = doc.toObject(WeightInfo.class);
                    weightList.add(obj);
                }

                weightAdapter.notifyDataSetChanged();



            }
        });



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
