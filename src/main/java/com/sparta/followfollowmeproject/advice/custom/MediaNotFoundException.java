package com.sparta.followfollowmeproject.advice.custom;

public class MediaNotFoundException extends RuntimeException {
    public MediaNotFoundException(String message) {
        super(message);
    }
}
