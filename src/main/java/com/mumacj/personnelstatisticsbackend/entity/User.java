package com.mumacj.personnelstatisticsbackend.entity;

import java.util.Date;

public class User {
    private String idCard;

    private String username;

    private String password;

    private String address;

    private Date registeDate;

    private Integer inTimes;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getRegisteDate() {
        return registeDate;
    }

    public void setRegisteDate(Date registeDate) {
        this.registeDate = registeDate;
    }

    public Integer getInTimes() {
        return inTimes;
    }

    public void setInTimes(Integer inTimes) {
        this.inTimes = inTimes;
    }
}