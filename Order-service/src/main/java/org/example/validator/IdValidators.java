package org.example.validator;

import org.example.exception.InvalidCustomerIdException;
import org.example.exception.InvalidOrderIdException;
import org.springframework.stereotype.Component;


public class IdValidators {

    public void validateOrderId(Long orderId){
        if(orderId <= 0) throw new InvalidOrderIdException("Order id is Invalid");
    }

    public void validateUserId(Long userId){
        if(userId <= 0) throw new InvalidCustomerIdException("Customer id is Invalid");
    }

}
