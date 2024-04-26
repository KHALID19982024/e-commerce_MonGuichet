package org.example.Service;

import org.example.DTO.request.OrderItemRequest;
import org.example.DTO.response.OrderItemResponseDto;
import org.example.Entity.OrderItems;

import java.time.LocalDateTime;

public interface OrderItemService {
    OrderItemResponseDto getOrderItemsById(Long OrderItemsId);
    OrderItemResponseDto createOrderItems(OrderItemRequest orderItemRequest);
    OrderItemResponseDto updateOrderItems(Long OrderItemsId, OrderItemRequest orderItemRequest);

    void deleteOrderItemsById(Long OrderItemsId) ;

}