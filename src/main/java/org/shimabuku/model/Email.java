package org.shimabuku.model;

public class Email {
    private int emailId;
    private String title;
    private String message;
    private User receiver;
    private User sender;

    public Email() {}

    public Email(int emailId, String title, String message, User receiver, User sender) {
        this.emailId = emailId;
        this.title = title;
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public int getId() { return emailId; }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null) throw new NullPointerException("The title must not be null");
        if (title.isEmpty()) throw new IllegalArgumentException("The title must not be empty");

        this.title = title;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        if (message == null) throw new NullPointerException("The message must not be null");
        if (message.isEmpty()) throw new IllegalArgumentException("The message must not be empty");

        this.message = message;
    }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
}
