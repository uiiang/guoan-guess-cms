package com.uiiang.entity;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public class GuessResultPreview {
    private List<GuessResultPreviewObj> homeWin;
    private List<GuessResultPreviewObj> draw;
    private List<GuessResultPreviewObj> homeLose;

    public List<GuessResultPreviewObj> getHomeWin() {
        return homeWin;
    }

    public void setHomeWin(List<GuessResultPreviewObj> homeWin) {
        this.homeWin = homeWin;
    }

    public List<GuessResultPreviewObj> getDraw() {
        return draw;
    }

    public void setDraw(List<GuessResultPreviewObj> draw) {
        this.draw = draw;
    }

    public List<GuessResultPreviewObj> getHomeLose() {
        return homeLose;
    }

    public void setHomeLose(List<GuessResultPreviewObj> homeLose) {
        this.homeLose = homeLose;
    }
}
