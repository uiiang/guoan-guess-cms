package com.uiiang.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fuliqiang on 2017/4/27.
 */
@Entity
public class NowMatchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private int roundNum;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date matchDateTime;
    private String matchLevel;
    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    private int homeResult;
    private int awayResult;
    private int status;//0,未开始, 1,进行中 2,已结束
    private Long matchDbId;
    private int joinNum;
    private int homeWinNum;
    private int awayWinNum;
    private int drawNum;
    private String homeEmblems;
    private String awayEmblems;

    public String getHomeEmblems() {
        return homeEmblems;
    }

    public void setHomeEmblems(String homeEmblems) {
        this.homeEmblems = homeEmblems;
    }

    public String getAwayEmblems() {
        return awayEmblems;
    }

    public void setAwayEmblems(String awayEmblems) {
        this.awayEmblems = awayEmblems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getMatchDbId() {
        return matchDbId;
    }

    public void setMatchDbId(Long matchDbId) {
        this.matchDbId = matchDbId;
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

    public Date getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(Date matchDateTime) {
        this.matchDateTime = matchDateTime;
    }
}
