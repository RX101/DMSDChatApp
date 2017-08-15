package com.example.a15041867.dmsdchatapp;

/**
 * Created by 15041867 on 15/8/2017.
 */

public class Messages {
    private String id;
    private String messageText;
    private String messageTime;
    private String messageUser;

    public Messages(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Messages(String messageText, String messageTime, String messageUser) {

        this.messageText = messageText;
        this.messageTime = messageTime;
        this.messageUser = messageUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
}
