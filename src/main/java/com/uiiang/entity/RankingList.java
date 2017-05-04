package com.uiiang.entity;

/**
 * Created by fuliqiang on 2017/5/4.
 */
public class RankingList {
    private long joinNum;
    private PlayerInfo playerInfo;
    private long totalScore;

    public RankingList(long joinNum, long totalScore, PlayerInfo playerInfo) {
        this.joinNum = joinNum;
        this.playerInfo = playerInfo;
        this.totalScore = totalScore;
    }

    public long getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(long joinNum) {
        this.joinNum = joinNum;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }
}
