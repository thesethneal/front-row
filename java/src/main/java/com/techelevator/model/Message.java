package com.techelevator.model;

import java.time.LocalDateTime;

public class Message {

    private long messageId;
    private String messageContent;
    private LocalDateTime messageTimeSent;
    private LocalDateTime messageTimeExpiration;
    private long messageSender; // band Id

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getMessageTimeSent() {
        return messageTimeSent;
    }

    public void setMessageTimeSent(LocalDateTime messageTimeSent) {
        this.messageTimeSent = messageTimeSent;
    }

    public LocalDateTime getMessageTimeExpiration() {
        return messageTimeExpiration;
    }

    public void setMessageTimeExpiration(LocalDateTime messageTimeExpiration) {
        this.messageTimeExpiration = messageTimeExpiration;
    }

    public long getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(long messageSender) {
        this.messageSender = messageSender;
    }
}
