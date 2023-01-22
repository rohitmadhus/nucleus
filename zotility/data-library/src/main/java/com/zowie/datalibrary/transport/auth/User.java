package com.zowie.datalibrary.transport.auth;

public class User {
    String username;
    String password;

    public User(){}

    public User(String username){
        this.username = username;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
