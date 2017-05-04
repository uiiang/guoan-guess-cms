package com.uiiang.entity;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public class GuessResultPreviewObj {
    private int homeResult;
    private int awayResult;
    private long count;

    private String result;
    public GuessResultPreviewObj(int homeResult, int awayResult, long count) {
        this.homeResult = homeResult;
        this.awayResult = awayResult;
        this.result = homeResult + ":" + awayResult;
        this.count = count;

    }

    public int getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(int homeResult) {
        this.homeResult = homeResult;
    }

    public int getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(int awayResult) {
        this.awayResult = awayResult;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
