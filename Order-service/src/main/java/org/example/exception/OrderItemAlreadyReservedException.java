package org.example.exception;

public class OrderItemAlreadyReservedException extends RuntimeException{
    public OrderItemAlreadyReservedException(String message) {
        super(message);
    }
}
