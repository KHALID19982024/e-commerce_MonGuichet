package org.example.exception;

public class OrderItemNotAvailableException extends RuntimeException{
    public OrderItemNotAvailableException(String message) {
        super(message);
    }
}
