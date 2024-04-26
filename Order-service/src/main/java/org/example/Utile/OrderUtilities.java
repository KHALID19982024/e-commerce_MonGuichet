package org.example.Utile;

import org.example.Entity.OrderItems;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtilities {

    public static BigDecimal countTotalPrice(List<OrderItems> items){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < items.size(); i++){
            total = total.add(items.get(i).getPrice());
        }
        return total;
    }
}
