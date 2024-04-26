package org.example.Utile;

import org.example.Entity.OrderItems;

import java.math.BigDecimal;

public class OrderItemUtilities {

    public static BigDecimal getSubTotalForItem(OrderItems orderItems, int quantity){
        return (orderItems.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}