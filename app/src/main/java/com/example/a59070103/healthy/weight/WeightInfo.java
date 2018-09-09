package com.example.a59070103.healthy.weight;

import java.util.Date;

public class WeightInfo {

    private float weight;
    private String addDate;
    private String status;

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public WeightInfo(float weight, String addDate, String status) {
        this.weight = weight;
        this.addDate = addDate;
        this.status = status;
    }

    public WeightInfo() {}



}
