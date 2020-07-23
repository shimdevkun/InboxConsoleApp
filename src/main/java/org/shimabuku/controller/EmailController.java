package org.shimabuku.controller;

import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

import java.util.List;

public class EmailController {
    private InMemoryInboxData db;
    private User activeUser;

    public EmailController(InMemoryInboxData db, int activeUserId) {
        this.db = db;
        this.activeUser = db.get(activeUserId);
    }

    public void create(String title, String message, User receiver) {
        db.add(title, message, receiver, activeUser);
    }

    public List<User> setReceiver() {
        return db.getAllUsers();
    }

    public boolean isValidContent(String content) {
        return content != null && !content.isEmpty();
    }
}
