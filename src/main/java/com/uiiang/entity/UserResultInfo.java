package com.uiiang.entity;

/**
 * Created by fuliqiang on 2017/4/22.
 */
public class UserResultInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    private int total;
    private int years;
}
