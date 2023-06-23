package com.mongotask.api.exception;

public class NoSuchFilterException extends RuntimeException {
    public NoSuchFilterException(String message) {
        super(message);
    }
}
