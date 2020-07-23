package org.shimabuku.model;

public class EmailDeleted {
    private int userId;
    private int emailId;

    public EmailDeleted(int userId, int emailId) {
        this.userId = userId;
        this.emailId = emailId;
    }
}
