package com.example.movie.Pojo;

import java.io.Serializable;

public class Safe implements Serializable {
    private Integer num;
    private String question;
    private String answer;

    public Safe() {
    }

    public Safe(Integer num, String question, String answer) {
        this.num = num;
        this.question = question;
        this.answer = answer;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Safe{" +
                "num=" + num +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public boolean isemtpy(){
        if (this.num==null||this.answer.equals("")||this.question.equals(""))
            return true;
        else
            return false;
    }
}
