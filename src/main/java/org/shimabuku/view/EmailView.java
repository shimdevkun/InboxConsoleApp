package org.shimabuku.view;

import org.shimabuku.controller.EmailController;
import org.shimabuku.model.Email;
import org.shimabuku.model.InboxActions;
import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class EmailView {
    private EmailController ctrEmail;
    private Scanner scanner;
    private User activeUser;

    public EmailView(InMemoryInboxData inboxData, int activeUserId) {
        ctrEmail = new EmailController(inboxData, activeUserId);
        activeUser = ctrEmail.getActiveUser();
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void printActions() {
        System.out.println("Select an action:");
        System.out.println("1. Send email");
        System.out.println("2. Check inbox");
        System.out.println("3. Log out");

        int option = 1;
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
            }
            else {
                option = Integer.parseInt(scanner.next());
                if (option < 0 || option > InboxActions.values().length) {
                    System.out.println("Please enter a valid number.");
                } else break;
            }
        }

        InboxActions action = ctrEmail.getAction(option);
        performAction(action);
    }

    private void performAction(InboxActions action) {
        switch (action) {
            case SEND_EMAIL:
                sendEmail();
                break;
            case CHECK_INBOX:
                checkInbox();
                break;
            case LOG_OUT:
                logOut();
                break;
        }
    }

    private void logOut() {
        System.out.println("User " + activeUser.getName() + " has successfully logged out.");
        activeUser = null;
    }

    //region sendEmail
    private void sendEmail() {
        System.out.println();

        User receiver;
        do { receiver = setReceiver(); }
        while (receiver == null);

        String title = setTitle();
        String message = setMessage();
        ctrEmail.create(title, message, receiver);
        printSuccessMessage();
    }

    private void printSuccessMessage() {
        System.out.println("Your email was send with success.");
        System.out.println();
    }

    private User setReceiver() {
        List<User> users = ctrEmail.setReceiver();
        System.out.println("Choose a receiver (1 to " + users.size() + "):");
        users.forEach(u -> System.out.println(u.getUserId() + ". " + u.getName()));

        while (scanner.hasNext() && !scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.next();
        }

        int id = scanner.nextInt();
        return users.stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
    }

    private String setTitle() {
        System.out.println("Enter a title:");
        String title;
        while (scanner.hasNext()) {
            title = scanner.next();
            if (!ctrEmail.isValidContent(title))
                System.out.println("Please enter a valid title");
            else
                return title;
        }
        return null;
    }

    private String setMessage() {
        System.out.println("Enter a message:");
        String message;
        while (scanner.hasNext()) {
            message = scanner.next();
            if (!ctrEmail.isValidContent(message))
                System.out.println("Please a valid message");
            else
                return message;
        }
        return null;
    }
    //endregion

    //region checkInbox
    private void checkInbox() {
        System.out.println();
        List<Email> emails = ctrEmail.checkInbox();
        if (emails.size() > 0) {
            System.out.println("Here are your emails " + activeUser.getName() + " :");
            AtomicInteger count = new AtomicInteger();
            emails.forEach(e -> {
                String newTag = "";
                if (!e.isRead()) newTag = " (NEW)";
                System.out.println("------------------------------");
                System.out.println("Message #" + count.incrementAndGet() + newTag);
                System.out.println("------------------------------");
                System.out.println("Title: " + e.getTitle());
                System.out.println();
                System.out.println("Message:");
                System.out.println(e.getMessage());
                System.out.println();
                System.out.println("From: " + e.getSender().getName());
                System.out.println("------------------------------");
                System.out.println();
                e.setIsRead();
            });
        }
        else {
            System.out.println("** You have no emails for now **");
            System.out.println();
        }
    }

    public boolean isLoggedIn() {
        return activeUser != null;
    }

    public void printNewEmails() {
        int newEmails = ctrEmail.getNewEmailsNumber();
        if (newEmails == 0)
            System.out.println("** No new emails **");
        else if (newEmails == 1)
            System.out.println("** You have " + newEmails + " new email **");
        else
            System.out.println("** You have " + newEmails + " new emails **");
        System.out.println();
    }
    //endregion
}
