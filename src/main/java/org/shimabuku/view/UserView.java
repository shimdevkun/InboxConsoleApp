package org.shimabuku.view;

import org.shimabuku.controller.UserController;
import org.shimabuku.service.InMemoryInboxData;

public class UserView {
    private UserController ctrUser;

    public UserView(InMemoryInboxData inboxData) {
        ctrUser = new UserController(inboxData);
    }

    public void logIn(int id) {
        ctrUser.setActiveUser(id);
        printWelcomeMessage();
    }

    private void printWelcomeMessage() {
        System.out.println("You are logged in as " + ctrUser.getActiveUser().getName());
    }
}
