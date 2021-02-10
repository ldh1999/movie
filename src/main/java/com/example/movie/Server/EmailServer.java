package com.example.movie.Server;

import com.example.movie.Pojo.State;

public interface EmailServer {
    public State SendCode(String email);
    public State re_Code(String email,String code);
}