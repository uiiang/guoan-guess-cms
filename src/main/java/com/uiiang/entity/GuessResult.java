package com.uiiang.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fuliqiang on 2017/4/22.
 */
@Entity
public class GuessResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;//提交时间
    private int homeResult;//主场进球数
    private int awayResult;//客场进球数
    private String resultType;//胜平负--WIN--DRAW--LOSE
    private int score;//单场个人得分

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerInfo playerInfo;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "MSCHE_ID")
    private MatchSchedule matchSchedule;


    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }


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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
