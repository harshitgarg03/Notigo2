package com.example.notigo;


public class Listitem {
    private String content;
    private String date;
    private String time;

    //Mandatory empty constructor for use of FirebaseUI
    public Listitem(){
    }

    public Listitem(String content, String date, String time) {
        this.content = content;
        this.date = date;
        this.time = time;

    }

    public String getContent(){
        return content;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}