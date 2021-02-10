package com.example.movie.Pojo;

import java.io.Serializable;

/*
    state:成功        400
          含有空值    250
          重复id      200
          查不到id    100
          密码错误    150
 */
public class State implements Serializable {
    private String state;
    private String uid;
    private String decision;

    @Override
    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                ", uid='" + uid + '\'' +
                ", decision='" + decision + '\'' +
                '}';
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public State(String state, String uid) {
        this.state = state;
        this.uid = uid;
    }

    public State() {
    }

    public State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
