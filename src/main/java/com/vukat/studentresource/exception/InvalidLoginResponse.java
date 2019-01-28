package com.vukat.studentresource.exception;

public class InvalidLoginResponse {

    private String username;

    private String password;

    public InvalidLoginResponse(){
        this.password = "Invalid password";
        this.username = "Invalid email";
    }

}
