package com.uiiang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Entity
public class MatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String chineseName;
    private int status;//0,未开始, 1,进行中 2,已结束

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    private String matchScope;

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

//    private String
}
