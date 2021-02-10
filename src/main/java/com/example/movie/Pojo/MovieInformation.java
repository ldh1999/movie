package com.example.movie.Pojo;

import java.io.Serializable;

public class MovieInformation implements Serializable {
    private Integer num;
    private String name;
    private String img;
    private String year;
    private String score;
    private String language;
    private String type;
    private String introduce;
    private String video;
    public MovieInformation() {
    }

    public MovieInformation(Integer num, String name, String img, String year, String score, String language, String type, String introduce, String video) {
        this.num = num;
        this.name = name;
        this.img = img;
        this.year = year;
        this.score = score;
        this.language = language;
        this.type = type;
        this.introduce = introduce;
        this.video = video;
    }

    public MovieInformation(String name, String img, String year, String score, String language, String type, String introduce) {
        this.name = name;
        this.img = img;
        this.year = year;
        this.score = score;
        this.language = language;
        this.type = type;
        this.introduce = introduce;
    }

    public boolean isempty(){
        if (this.name.equals("")||this.img.equals("")||this.score.equals("")||this.year.equals("")||this.language.equals("")||this.type.equals("")||this.introduce.equals(""))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "MovieInformation{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", year='" + year + '\'' +
                ", score='" + score + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", introduce='" + introduce + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
