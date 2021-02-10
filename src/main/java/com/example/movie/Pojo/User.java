package com.example.movie.Pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer num;
    private String name;
    private String email;
    private String password;

    public boolean isempty(){
        if(this.name.equals("")||this.email.equals("")||this.password.equals("")){
            return true;
        }else
            return false;
    }

    public User() {
    }

    public User(Integer num, String name, String email, String password) {
        this.num = num;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
