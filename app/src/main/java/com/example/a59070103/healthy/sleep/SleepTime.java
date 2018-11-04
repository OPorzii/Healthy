package com.example.a59070103.healthy.sleep;

import android.provider.BaseColumns;

import java.io.Serializable;
import java.util.Date;

public class SleepTime implements Serializable {
    private int id;
    private String date;
    private String sleepTime;
    private String wakeTime;
    private String diffTime;

    public static final String DATABASE_NAME = "sleeptime15.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE = "sleep_time";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String DATE = "date";
        public static final String TIME_SLEEP = "time_sleep";
        public static final String TIME_WAKE = "time_wake";
        public static final String TIME_DIFF = "time_diff";
    }

    public SleepTime(String date, String sleepTime, String wakeTime, String diffTime) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
        this.diffTime = diffTime;
    }

    public SleepTime() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
