package com.example.movie.Pojo;

import java.io.Serializable;

public class Recommend implements Serializable {
    private Integer usernum;
    private Integer action;
    private Integer science;
    private Integer comedy;
    private Integer suspense;
    private Integer dracula;
    private Integer disaster;

    public Recommend() {
    }

    public Recommend(Integer usernum, Integer action, Integer science, Integer comedy, Integer suspense, Integer dracula, Integer disaster) {
        this.usernum = usernum;
        this.action = action;
        this.science = science;
        this.comedy = comedy;
        this.suspense = suspense;
        this.dracula = dracula;
        this.disaster = disaster;
    }

    public Recommend(Integer usernum) {
        this.usernum = usernum;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "usernum=" + usernum +
                ", action=" + action +
                ", science=" + science +
                ", comedy=" + comedy +
                ", suspense=" + suspense +
                ", dracula=" + dracula +
                ", disaster=" + disaster +
                '}';
    }

    public Integer getUsernum() {
        return usernum;
    }

    public void setUsernum(Integer usernum) {
        this.usernum = usernum;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getScience() {
        return science;
    }

    public void setScience(Integer science) {
        this.science = science;
    }

    public Integer getComedy() {
        return comedy;
    }

    public void setComedy(Integer comedy) {
        this.comedy = comedy;
    }

    public Integer getSuspense() {
        return suspense;
    }

    public void setSuspense(Integer suspense) {
        this.suspense = suspense;
    }

    public Integer getDracula() {
        return dracula;
    }

    public void setDracula(Integer dracula) {
        this.dracula = dracula;
    }

    public Integer getDisaster() {
        return disaster;
    }

    public void setDisaster(Integer disaster) {
        this.disaster = disaster;
    }
}
