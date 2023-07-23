package com.sparta.followfollowmeproject.advice.custom;

public class NotExistException extends RuntimeException{
    public NotExistException(String message) {
        super(message);
    }
}

