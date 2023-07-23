package com.sparta.followfollowmeproject.advice.custom;

public class SelfFollowNotAllowedException extends RuntimeException{
    public SelfFollowNotAllowedException(String message) {
        super(message);
    }
}
