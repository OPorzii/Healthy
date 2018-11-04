package com.example.a59070103.healthy.sleep;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a59070103.healthy.R;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.MyViewHolder> {

    private Context context;
    private List<SleepTime> sleepList;
    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView time_sleep;
        public TextView time_wake;
        public TextView time_diff;

        public MyViewHolder(View view,final onItemClickListener listener) {
            super(view);
            date = view.findViewById(R.id.date_sleep);
            time_sleep = view.findViewById(R.id.sleep_text);
            time_wake = view.findViewById(R.id.wake_text);
            time_diff = view.findViewById(R.id.diff_text);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

        public SleepAdapter(Context context, List<SleepTime> sleepList) {
            this.context = context;
            this.sleepList = sleepList;
        }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sleep_form_items, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(itemView, onItemClickListener);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SleepTime sleepTime = sleepList.get(position);

        holder.date.setText(sleepTime.getDate());

        holder.time_sleep.setText(sleepTime.getSleepTime());

        holder.time_wake.setText(sleepTime.getWakeTime());

        holder.time_diff.setText(sleepTime.getDiffTime());

    }


    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.onItemClickListener = listener;
    }
}
