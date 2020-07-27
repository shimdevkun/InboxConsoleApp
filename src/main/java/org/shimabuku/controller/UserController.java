package org.shimabuku.controller;

import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

import java.util.List;

public class UserController {
    private InMemoryInboxData db;
    private User activeUser;

    public UserController(InMemoryInboxData db) {
        this.db = db;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int id) {
        activeUser = db.get(id);
    }

    public User getUser(int userId) {
        return db.get(userId);
    }

    public List<User> getAll() {
        return db.getAllUsers();
    }
}
