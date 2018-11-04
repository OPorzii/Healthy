package com.example.a59070103.healthy.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.a59070103.healthy.sleep.SleepTime;

import java.util.ArrayList;

public class DBLite extends SQLiteOpenHelper {


    public DBLite(@Nullable Context context) {
        super(context, SleepTime.DATABASE_NAME, null, SleepTime.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

                String CREATE_SLEEP_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                SleepTime.TABLE,
                SleepTime.Column.ID,
                SleepTime.Column.DATE,
                SleepTime.Column.TIME_SLEEP,
                SleepTime.Column.TIME_WAKE,
                SleepTime.Column.TIME_DIFF);
                db.execSQL(CREATE_SLEEP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SleepTime.TABLE);
        onCreate(db);
    }

    public void addSleepTime(SleepTime sleepTimeItem) {
        // get database
        SQLiteDatabase db = getWritableDatabase();

        // put values into row
        ContentValues values = new ContentValues();
        values.put(SleepTime.Column.DATE, sleepTimeItem.getDate());
        values.put(SleepTime.Column.TIME_SLEEP, sleepTimeItem.getSleepTime());
        values.put(SleepTime.Column.TIME_WAKE, sleepTimeItem.getWakeTime());
        values.put(SleepTime.Column.TIME_DIFF, sleepTimeItem.getDiffTime());

        // insert to database
        db.insert(SleepTime.TABLE, null, values);
    }

    public ArrayList<SleepTime> getSleepData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SleepTime> sleepTimeItems = new ArrayList<>();


        Cursor data = db.query(SleepTime.TABLE,null, null, null, null, null, null);

        if (data != null) {
            data.moveToFirst();
        }

        while(!data.isAfterLast()) {
            SleepTime item = new SleepTime();
            item.setId(data.getInt(0));
            item.setDate(data.getString(1));
            item.setSleepTime(data.getString(2));
            item.setWakeTime(data.getString(3));
            item.setDiffTime(data.getString(4));
            sleepTimeItems.add(item);
            data.moveToNext();
        }

        db.close();

        return sleepTimeItems;
    }



    public void update(SleepTime item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SleepTime.Column.ID, item.getId());
        values.put(SleepTime.Column.DATE, item.getDate());
        values.put(SleepTime.Column.TIME_SLEEP, item.getSleepTime());
        values.put(SleepTime.Column.TIME_WAKE, item.getWakeTime());
        values.put(SleepTime.Column.TIME_DIFF, item.getDiffTime());

        db.update(SleepTime.TABLE, values, SleepTime.Column.ID +" = " + item.getId(), null);

        db.close();
    }
}
