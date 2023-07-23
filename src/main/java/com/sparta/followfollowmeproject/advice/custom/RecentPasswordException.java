package com.sparta.followfollowmeproject.advice.custom;

public class RecentPasswordException extends RuntimeException{
    public RecentPasswordException(String message) {
        super(message);
    }
}
