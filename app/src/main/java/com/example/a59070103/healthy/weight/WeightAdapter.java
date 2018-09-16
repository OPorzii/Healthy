package com.example.a59070103.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070103.healthy.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter<WeightInfo> {

    List<WeightInfo> weightInfos =  new ArrayList<>();
    Context context;

    public WeightAdapter(Context context, int resouce, List<WeightInfo> objects){
        super(context, resouce, objects);
        this.weightInfos = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.weight_form_items,
                parent,
                false);


        TextView date = weightItem.findViewById(R.id.weight_item_date);
        TextView weight = weightItem.findViewById(R.id.weight_item_weight);
        TextView status = weightItem.findViewById(R.id.weight_item_status);

        WeightInfo row = weightInfos.get(position);
        date.setText(row.getAddDate());
        weight.setText(""+ row.getWeight());
        status.setText(""+ row.getStatus());
        return weightItem;
    }
}
