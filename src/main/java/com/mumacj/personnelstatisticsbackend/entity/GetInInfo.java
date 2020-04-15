package com.mumacj.personnelstatisticsbackend.entity;

import java.util.Date;

public class GetInInfo {
    private String id;

    private String idCard;

    private String name;

    private String address;

    private Date inTime;

    private Double temperature;

    private String wheLeave;

    private String healthCode;

    private String registePeople;

    private Date registTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWheLeave() {
        return wheLeave;
    }

    public void setWheLeave(String wheLeave) {
        this.wheLeave = wheLeave == null ? null : wheLeave.trim();
    }

    public String getHealthCode() {
        return healthCode;
    }

    public void setHealthCode(String healthCode) {
        this.healthCode = healthCode == null ? null : healthCode.trim();
    }

    public String getRegistePeople() {
        return registePeople;
    }

    public void setRegistePeople(String registePeople) {
        this.registePeople = registePeople == null ? null : registePeople.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    @Override
    public String toString() {
        return "GetInInfo{" +
                "id='" + id + '\'' +
                ", idCard='" + idCard + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", inTime=" + inTime +
                ", temperature=" + temperature +
                ", wheLeave='" + wheLeave + '\'' +
                ", healthCode='" + healthCode + '\'' +
                ", registePeople='" + registePeople + '\'' +
                ", registTime=" + registTime +
                '}';
    }
}