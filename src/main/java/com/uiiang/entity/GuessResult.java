package com.uiiang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by fuliqiang on 2017/4/22.
 */
@Entity
public class GuessResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String userId;
    private long submitTime;
    private String matchId;
    private String homeResult;
    private String awayResult;
    private int score;//单场个人得分

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(String homeResult) {
        this.homeResult = homeResult;
    }

    public String getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(String awayResult) {
        this.awayResult = awayResult;
    }
}
