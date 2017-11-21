package com.test.servlet_jiaocheng.model;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class Article {
    private int id;
    private String name;
    private String faDingDaiBiao;
    private String zhuCeZiBen;
    private String chengLiRiQi;
    private String address;
    private String jingYingFanWei;
    private String email;
    private String site;
    private boolean deleted;
    private Timestamp createTime;
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaDingDaiBiao() {
        return faDingDaiBiao;
    }

    public void setFaDingDaiBiao(String faDingDaiBiao) {
        this.faDingDaiBiao = faDingDaiBiao;
    }

    public String getZhuCeZiBen() {
        return zhuCeZiBen;
    }

    public void setZhuCeZiBen(String zhuCeZiBen) {
        this.zhuCeZiBen = zhuCeZiBen;
    }

    public String getChengLiRiQi() {
        return chengLiRiQi;
    }

    public void setChengLiRiQi(String chengLiRiQi) {
        this.chengLiRiQi = chengLiRiQi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJingYingFanWei() {
        return jingYingFanWei;
    }

    public void setJingYingFanWei(String jingYingFanWei) {
        this.jingYingFanWei = jingYingFanWei;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
