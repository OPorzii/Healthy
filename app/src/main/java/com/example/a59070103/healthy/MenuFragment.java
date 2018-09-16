package com.example.a59070103.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a59070103.healthy.weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuFragment extends Fragment{


    ArrayList<String> _menu = new ArrayList<>();



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        _menu.clear();
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Setup");


        ListView menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_expandable_list_item_1,_menu
        );
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Click on menu = "+ _menu.get(i));
                if(i==0){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null)
                            .commit();
                } else if(i==1){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        signoutBtn();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    void signoutBtn(){
        TextView _sightoutbtn = (TextView) getView().findViewById(R.id.sighout_btn);
        _sightoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
