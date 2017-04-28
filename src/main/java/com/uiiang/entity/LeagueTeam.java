package com.uiiang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by fuliqiang on 2017/4/22.
 */
@Entity
public class LeagueTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String chineseName;
    public String city;
    public String stadiumName;
    private String emblems;

    public String getEmblems() {
        return emblems;
    }

    public void setEmblems(String emblems) {
        this.emblems = emblems;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }
}
