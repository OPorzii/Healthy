package com.example.a59070103.healthy.sleep;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a59070103.healthy.DB.DBLite;
import com.example.a59070103.healthy.R;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SleepForm extends Fragment  {

    DBLite myDB;
    private Bundle bundle;
    private Calendar calendar;
    private String txtDate, txtTimeSleep, txtTimeWake,txtDiff;
    private TextView _date,_timeSleep,_timeWake;
    private SleepTime editSleepTime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sleep_form, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundle =  getArguments();
        myDB = new DBLite(getActivity());
        initSaveBtn();
        initBackBtn();

        _date = (TextView) getView().findViewById(R.id.date_sleep);
        _timeSleep = (TextView) getView().findViewById(R.id.time_sleep);
        _timeWake = (TextView) getView().findViewById(R.id.time_wake);


        _date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(_date);
            }
        });

        _timeSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(_timeSleep);
            }
        });

        _timeWake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setTime(_timeWake);
            }
        });

        if(bundle != null){
            loadPrevData();
        }


    }

    private void setDate(final  TextView textView){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),listener,year,month,day);
        datePicker.show();

    }
    private void setTime(final TextView textView) {
        // get current time
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // show time picker dialog
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = String.format("%02d:%02d", hourOfDay, minute);
                textView.setText(time);
            }
        };

        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), listener, hour, minute,false);
        timePicker.show();
    }



    void initSaveBtn(){

        Button _addBtn = (Button) getView().findViewById(R.id.sleep_save_btn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtDate = _date.getText().toString();
                String txtTimeSleep = _timeSleep.getText().toString();
                String txtTimeWake = _timeWake.getText().toString();

                if(txtDate.isEmpty() || txtTimeSleep.isEmpty() || txtTimeSleep.isEmpty()){
                    Toast.makeText(getActivity(), "Don't Empty Fill", Toast.LENGTH_SHORT).show();
                } else {
                    calculateTime();
                    SleepTime sleepTime = new SleepTime(txtDate,txtTimeSleep,txtTimeWake,txtDiff);

                    if(bundle!=null){
                        sleepTime.setId(editSleepTime.getId());
                        myDB.update(sleepTime);
                        Log.d("UPDATE_RES",""+editSleepTime.getId());
                        Toast.makeText(getActivity(), "แก้ไขข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new SleepFragment())
                                .addToBackStack(null).commit();

                    } else {
                        myDB.addSleepTime(sleepTime);
                        Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย!", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new SleepFragment())
                                .addToBackStack(null).commit();
                    }


                }

            }
        });


    }

    void initBackBtn(){
        Button _backBtn = (Button) getView().findViewById(R.id.back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null).commit();
            }
        });
    }


    private String calculateTime() {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String start = _timeSleep.getText().toString();
        String stop = _timeWake.getText().toString();
        Date date1;
        Date date2;
        String hDuration = "";
        String mDuration = "";

        try {
            date1 = format.parse(start);
            date2 = format.parse(stop);
            DateTime dateTime1 = new DateTime(date1);
            DateTime dateTime2 = new DateTime(date2);

            int hour = Hours.hoursBetween(dateTime1, dateTime2).getHours() % 24;
            int minute = Minutes.minutesBetween(dateTime1, dateTime2).getMinutes() % 60;
            Log.d("Sleep Form", dateTime1.toString() + " " + dateTime2.toString());

            if (hour < 0) {
                hDuration = String.format("%02d", 24 + hour);
            } else {
                hDuration = String.format("%02d", hour);
            }
            if (minute < 0) {
                mDuration = String.format("%02d", 60 + minute);
            } else {
                mDuration = String.format("%02d", minute);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

         txtDiff = String.format("%s:%s", hDuration, mDuration);
        return txtDiff;
    }


    private void loadPrevData(){
            editSleepTime = (SleepTime) bundle.getSerializable("SleepItem");
            _date.setText(editSleepTime.getDate());
            _timeSleep.setText(editSleepTime.getSleepTime());
            _timeWake.setText(editSleepTime.getWakeTime());

    }


}
