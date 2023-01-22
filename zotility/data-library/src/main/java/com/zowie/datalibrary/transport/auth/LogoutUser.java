package com.zowie.datalibrary.transport.auth;

public class LogoutUser extends User{
    String message;

    public LogoutUser(){}

    public LogoutUser(String message,String username){
        super(username);
        this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
