package com.example.springstompchat.entity;

public class ChatMessage {
    public String user;
    public String text;

    public ChatMessage() {
    }

    public ChatMessage(String text) {
        this.text = text;
    }

    public ChatMessage(String user, String text) {
        this.user = user;
        this.text = text;
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
}
