package com.sparta.followfollowmeproject.advice.custom;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
