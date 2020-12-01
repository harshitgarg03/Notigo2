package com.example.notigo;

public class Userhelperclass {
    String content, date, time;

    public Userhelperclass(){

    }

    public Userhelperclass(String content, String date, String time) {
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
