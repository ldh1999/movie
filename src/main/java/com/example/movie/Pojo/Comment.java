package com.example.movie.Pojo;

import java.io.Serializable;

public class Comment implements Serializable {
    private Integer num;
    private Integer commentator;
    private String content;
    private String time;
    private Integer movieNum;
    public Comment() {
    }

    public Comment(Integer commentator, String content, String time, Integer movieNum) {
        this.commentator = commentator;
        this.content = content;
        this.time = time;
        this.movieNum = movieNum;
    }

    public Comment(Integer num, Integer commentator, String content, String time, Integer movieNum) {
        this.num = num;
        this.commentator = commentator;
        this.content = content;
        this.time = time;
        this.movieNum = movieNum;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "num=" + num +
                ", commentator='" + commentator + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public Integer getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(Integer movieNum) {
        this.movieNum = movieNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCommentator() {
        return commentator;
    }

    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
