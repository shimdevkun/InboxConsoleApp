package org.shimabuku.controller;

import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

public class UserController {
    private InMemoryInboxData inboxData;
    private User activeUser;

    public UserController(InMemoryInboxData inboxData) {
        this.inboxData = inboxData;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int id) {
        activeUser = inboxData.get(id);
    }
}
