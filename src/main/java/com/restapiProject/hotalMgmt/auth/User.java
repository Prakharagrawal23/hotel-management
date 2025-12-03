package com.restapiProject.hotalMgmt.auth;

public class User {
    // Instance members (fields)
    private int id;
    private String username;
    private String password;
    private String role;

    // Constructor
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public User() {}

    // Getter and Setter for id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
