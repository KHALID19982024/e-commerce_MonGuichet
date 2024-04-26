package org.example.Service;

import org.example.DTO.request.OrderRequest;
import org.example.DTO.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderById(Long orderId);
    OrderResponseDto createOrder(OrderRequest orderRequest);
    OrderResponseDto updateOrder(Long orderId, OrderRequest orderRequest);
    void cancelOrderById(Long orderId);
    List<OrderResponseDto> getOrderHistoryByUserId(Long userId);


}
