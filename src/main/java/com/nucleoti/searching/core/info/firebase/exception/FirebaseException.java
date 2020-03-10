package com.nucleoti.searching.core.info.firebase.exception;

public class FirebaseException extends RuntimeException {

    private final int status;

    public FirebaseException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}