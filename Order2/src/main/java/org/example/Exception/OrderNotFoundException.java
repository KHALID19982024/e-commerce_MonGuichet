package org.example.Exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int orderId) {
        super("Order with order id: "+ orderId+" is not found");
    }
}
