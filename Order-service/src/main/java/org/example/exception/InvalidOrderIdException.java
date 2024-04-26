package org.example.exception;

public class InvalidOrderIdException extends RuntimeException {
    public InvalidOrderIdException(String s) {
        super(s);
    }
}