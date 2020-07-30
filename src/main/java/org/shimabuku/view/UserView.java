package org.shimabuku.view;

import org.shimabuku.controller.UserController;
import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private UserController ctrUser;
    private Scanner scanner;

    public UserView(InMemoryInboxData inboxData) {
        ctrUser = new UserController(inboxData);
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void welcomeUser() {
        System.out.println("Welcome to the inbox app!");
    }

    public boolean runInbox() {
        System.out.println("Press l to login or q to quit");
        while (scanner.hasNext()) {
            String option = scanner.next();
            if (option.equals("l")) {
                identifyUser();
                break;
            }
            else if (option.equals("q")) {
                return false;
            } else {
                System.out.println("Press l to login or q to quit");
            }
        }
        return true;
    }

    private void identifyUser() {
        System.out.println("Please select a user: ");
        printUsers();

        int userId = 0;
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a whole number.");
                scanner.next();
            }
            else {
                userId = scanner.nextInt();
                if (isValidUser(userId)) break;
                printUserNotFoundError();
            }
        }

        logIn(userId);
    }

    private void printUsers() {
        List<User> users = ctrUser.getAll();
        for (User u : users) {
            String output = String.format("%d. %s", u.getUserId(), u.getName());
            System.out.println(output);
        }
    }

    public void printUserNotFoundError() {
        System.out.println("The user chosen does not exist. Provide a number between 1 and " + ctrUser.getAll().size() + ".");
    }

    public boolean isValidUser(int userId) {
        return ctrUser.getUser(userId) != null;
    }

    public void logIn(int id) {
        ctrUser.setActiveUser(id);
        printWelcomeMessage();
    }

    public int getUserId() {
        return ctrUser.getActiveUser().getUserId();
    }

    private void printWelcomeMessage() {
        System.out.println("You are logged in as " + ctrUser.getActiveUser().getName() + ".");
    }
}
