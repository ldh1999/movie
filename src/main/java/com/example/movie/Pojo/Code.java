package com.example.movie.Pojo;

import java.io.Serializable;

public class Code implements Serializable {
    private String email;
    private String code;
    private String timeout;

    public Code() {
    }

    public Code(String email, String code, String timeout) {
        this.email = email;
        this.code = code;
        this.timeout = timeout;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Code{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }
}
