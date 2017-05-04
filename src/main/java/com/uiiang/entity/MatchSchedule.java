package com.uiiang.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fuliqiang on 2017/4/22.
 */
@Entity
public class MatchSchedule {
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
    private String homeEmblems;
    private String awayEmblems;
    private String stadiumName;
    private int homeResult;
    private int awayResult;
    private String resultType;
    private int status;//0,未开始, 1,进行中 2,已结束  3,已计算

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Long getId() {
        return id;
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

    public Date getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(Date matchDateTime) {
        this.matchDateTime = matchDateTime;
    }
}
