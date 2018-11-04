package com.example.a59070103.healthy.sleep;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a59070103.healthy.DB.DBLite;
import com.example.a59070103.healthy.R;

import java.util.ArrayList;

public class SleepFragment extends Fragment {


    private ArrayList<SleepTime> sleepList = new ArrayList<>();
    private DBLite mydb ;
    private RecyclerView sleepShowList;
    private SleepAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Nullable
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initaddBtn();

        sleepShowList = getActivity().findViewById(R.id.sleep_list);

        sleepList.clear();

        mydb  = new DBLite(getActivity());
        sleepList.addAll(mydb.getSleepData());

        mAdapter = new SleepAdapter(getActivity(), sleepList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        sleepShowList.setLayoutManager(mLayoutManager);
        sleepShowList.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));


        sleepShowList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SleepAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SleepTime curSleepTime = sleepList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("SleepItem", curSleepTime);

                Fragment sleepForm = new SleepForm();
                sleepForm.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, sleepForm)
                        .addToBackStack(null).commit();
            }
        });




    }




    private void initaddBtn(){
        Button _addBtn = (Button) getView().findViewById(R.id.add_btnSleep);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepForm())
                        .addToBackStack(null).commit();
            }
        });
    }






}
