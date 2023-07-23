package com.sparta.followfollowmeproject.advice.custom;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
