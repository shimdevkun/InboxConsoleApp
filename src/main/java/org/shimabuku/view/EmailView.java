package org.shimabuku.view;

import org.shimabuku.controller.EmailController;
import org.shimabuku.model.InboxActions;
import org.shimabuku.model.User;
import org.shimabuku.service.InMemoryInboxData;

import java.util.List;
import java.util.Scanner;

public class EmailView {
    private EmailController ctrEmail;
    private Scanner scanner;
    private User activeUser;

    public EmailView(InMemoryInboxData inboxData, int activeUserId) {
        ctrEmail = new EmailController(inboxData, activeUserId);
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void createEmail() {
        System.out.println("You've chosen to send an email");
        System.out.println("Enter a title: ");
        String title = scanner.next();
    }

    public void printActions() {
        System.out.println("Select an action:");
        System.out.println("1. Send email");

        while (scanner.hasNext() && !scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.next();
        }

        scanner.next();
        performAction(InboxActions.SEND_EMAIL);
    }

    private void performAction(InboxActions action) {
        switch (action) {
            case SEND_EMAIL:
                sendEmail();
                break;
        }
    }

    private void sendEmail() {
        System.out.println();
        User receiver = setReceiver();
        String title = setTitle();
        String message = setMessage();
        ctrEmail.create(title, message, receiver);
        printSuccessMessage();
    }

    private void printSuccessMessage() {
        System.out.println("Your email was send with success.");
    }

    private User setReceiver() {
        List<User> users = ctrEmail.setReceiver();
        System.out.println("Choose a receiver:");
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
}
