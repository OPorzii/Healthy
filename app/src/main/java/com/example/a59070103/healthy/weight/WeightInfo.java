package com.example.a59070103.healthy.weight;

import java.util.Date;

public class WeightInfo {

    private float weight;
    private String date;
    private String status;


    public WeightInfo() {}


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WeightInfo(float weight, String date, String status) {
        this.weight = weight;
        this.date = date;
        this.status = status;
    }





}
