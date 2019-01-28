package com.vukat.studentresource.exception;

public class UsernameAlreadyExistResponse {

    private String username;

    public UsernameAlreadyExistResponse(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
