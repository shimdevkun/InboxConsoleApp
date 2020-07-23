package org.shimabuku.service;

import org.shimabuku.model.Email;
import org.shimabuku.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryInboxData {
    private List<User> users;
    private List<Email> emails;

    public InMemoryInboxData() {
        users = new ArrayList<>(Arrays.asList(
                new User(1, "Jack"),
                new User(2, "Chrissy"),
                new User(3, "Max"),
                new User(4, "Lily")
        ));

        emails = new ArrayList<>(Arrays.asList(
                new Email(1, "First Email", "How are you?", users.get(0), users.get(1)),
                new Email(2, "Homework :/", "Hey, I need some help. Wanna come over?", users.get(1), users.get(3)),
                new Email(3, "Soccer Practice", "Just a friendly reminder to come tomorrow at the stadium c:", users.get(3), users.get(2)),
                new Email(4, "Sunday Party", "Hey, wanna join us at 9:00 p.m. this Sunday?", users.get(3), users.get(2))
        ));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User get(int userId) {
        return users.stream()
                .filter(u -> u.getUserId() == userId).findFirst().orElse(null);
    }

    public List<Email> getAllEmailsFrom(int userId) {
        return emails.stream()
                .filter(e -> e.getReceiver().getUserId() == userId).collect(Collectors.toList());
    }

    public void add(String title, String message, User receiver, User sender) {
        Email email = new Email(emails.size() + 1, title, message, receiver, sender);
        emails.add(email);
    }
}
