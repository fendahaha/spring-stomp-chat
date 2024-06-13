package com.example.springstompchat.entity;

public class ChatMessage {
    public String user;
    public String text;

    public Integer time;

    public ChatMessage() {
    }

    public ChatMessage(String text) {
        this.text = text;
    }

    public ChatMessage(String user, String text) {
        this.user = user;
        this.text = text;
    }

    public ChatMessage(String user, String text, Integer time) {
        this.user = user;
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
