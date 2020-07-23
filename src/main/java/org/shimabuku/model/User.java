package org.shimabuku.model;

public class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getName() { return name; }
    public int getUserId() { return userId; }
}
