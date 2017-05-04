package com.uiiang.entity;

import javax.persistence.*;

/**
 * Created by fuliqiang on 2017/4/27.
 */
@Entity
public class NowMatchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private int status;//0,未开始, 1,进行中 2,已结束

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "MSCHE_ID")
    private MatchSchedule matchSchedule;
    private int joinNum;
    private int homeWinNum;
    private int awayWinNum;
    private int drawNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MatchSchedule getMatchSchedule() {
        return matchSchedule;
    }

    public void setMatchSchedule(MatchSchedule matchSchedule) {
        this.matchSchedule = matchSchedule;
    }

    public int getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(int joinNum) {
        this.joinNum = joinNum;
    }

    public int getHomeWinNum() {
        return homeWinNum;
    }

    public void setHomeWinNum(int homeWinNum) {
        this.homeWinNum = homeWinNum;
    }

    public int getAwayWinNum() {
        return awayWinNum;
    }

    public void setAwayWinNum(int awayWinNum) {
        this.awayWinNum = awayWinNum;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(int drawNum) {
        this.drawNum = drawNum;
    }
}
