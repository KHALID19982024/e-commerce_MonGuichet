package org.example.Service.Impl;

import lombok.AllArgsConstructor;
import org.example.DTO.request.OrderItemRequest;
import org.example.DTO.response.OrderItemResponseDto;
import org.example.Entity.OrderItems;
import org.example.Mapper.MappingProfile;
import org.example.Repository.OrderItemRepo;
import org.example.Service.OrderItemService;
import org.example.exception.OrderItemAlreadyReservedException;
import org.example.exception.OrderItemNotAvailableException;
import org.example.exception.OrderItemNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderItemsServiceImpl implements OrderItemService {

    private final OrderItemRepo orderItemRepo;
    @Override
    public OrderItemResponseDto getOrderItemsById(Long orderItemId) {
        var orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order Item not found"));
        return MappingProfile.mapToOrderItemResponseDto(orderItem);
    }



    @Override
    public OrderItemResponseDto createOrderItems(OrderItemRequest orderItemRequest) {
        Long orderItemsId = orderItemRequest.getOrderItemsId();
        Long ticketId = orderItemRequest.getTicketId();

        BigDecimal price = orderItemRequest.getPrice();
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid price value");
        }

        BigDecimal subPrice = price.multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));

        OrderItems createOrderItem = MappingProfile.mapToOrderItemsEntity(orderItemRequest);
        createOrderItem.setSubPrice(subPrice); // Set subTotal in the entity
        OrderItems savedOrderItem = orderItemRepo.save(createOrderItem);

        // Map the saved entity to DTO and return
        return MappingProfile.mapToOrderItemResponseDto(savedOrderItem);
    }




    @Override
        public OrderItemResponseDto updateOrderItems(Long OrderItemsId, OrderItemRequest orderItemRequest) {
        OrderItems orderItems = orderItemRepo.findById(OrderItemsId)
                .orElseThrow(() -> new OrderItemNotFoundException("order item not found"));


        BigDecimal price = orderItems.getPrice();
        BigDecimal subPrice = price.multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));

        orderItems.setSubPrice(subPrice);

        orderItems.setTicketId(orderItemRequest.getTicketId());
        //
        orderItems.setPrice(orderItemRequest.getPrice());
        orderItems.setQuantity(orderItemRequest.getQuantity());

        // Save the updated rentalItem entity and map it to DTO
        return MappingProfile.mapToOrderItemResponseDto(orderItemRepo.save(orderItems));
    }



    @Override
    public void deleteOrderItemsById(Long OrderItemsId) {
        var orderItem = orderItemRepo.findById(OrderItemsId)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found"));
        orderItemRepo.delete(orderItem);

    }
}