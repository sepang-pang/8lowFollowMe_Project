package com.sparta.followfollowmeproject.advice.custom;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message) {
        super(message);
    }
}
