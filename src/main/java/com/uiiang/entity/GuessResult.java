package com.uiiang.entity;

import javax.persistence.*;

/**
 * Created by fuliqiang on 2017/4/22.
 */
@Entity
public class GuessResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String userId;//weixin user id
    private long submitTime;//提交时间
    private String homeResult;//主场进球数
    private String awayResult;//客场进球数
    private String resultType;//胜平负--WIN--DRAW--LOSE
    private int score;//单场个人得分

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MSCHE_ID")
    private MatchSchedule matchSchedule;

    public MatchSchedule getMatchSchedule() {
        return matchSchedule;
    }

    public void setMatchSchedule(MatchSchedule matchSchedule) {
        this.matchSchedule = matchSchedule;
    }

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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
