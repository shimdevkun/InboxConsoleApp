package org.shimabuku.model;

public class Email {
    private String title;
    private String message;
    private User receiver;
    private User sender;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
}
