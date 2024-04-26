package org.example.Service.Impl;

import lombok.AllArgsConstructor;
import org.example.DTO.request.OrderItemRequest;
import org.example.DTO.request.OrderRequest;
import org.example.DTO.response.OrderItemResponseDto;
import org.example.DTO.response.OrderResponseDto;
import org.example.Entity.Order;
import org.example.Mapper.MappingProfile;
import org.example.Repository.OrderRepository;
import org.example.Service.OrderService;
import org.example.exception.InvalidDateRangeException;
import org.example.exception.OrderNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
//    IdValidators idValidators;

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<OrderResponseDto> orderResponseDtos = orderRepository.findAll()
                .stream()
                .map(MappingProfile::mapToOrderResponseDto)
                .collect(Collectors.toList());

        orderResponseDtos.forEach(this::calculateTotalPrice);
        return orderResponseDtos;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
//        idValidators.validateOrderId(orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        OrderResponseDto orderResponseDto = MappingProfile.mapToOrderResponseDto(order);
        calculateTotalPrice(orderResponseDto);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequest orderRequest) {
        Order order = MappingProfile.mapToOrderEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);
        OrderResponseDto orderResponseDto = MappingProfile.mapToOrderResponseDto(savedOrder);
        calculateTotalPrice(orderResponseDto);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setOrderDate(orderRequest.getOrderDate());


        Order updatedOrder = orderRepository.save(order);

        OrderResponseDto orderResponseDto = MappingProfile.mapToOrderResponseDto(updatedOrder);

        calculateTotalPrice(orderResponseDto);

        return orderResponseDto;
    }


    @Override
    public void cancelOrderById(Long OrderId) {
        var order = orderRepository.findById(OrderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderResponseDto> getOrderHistoryByUserId(Long userId) {
        List<OrderResponseDto> orderHistory = orderRepository.findByUserId(userId)
                .stream()
                .map(MappingProfile::mapToOrderResponseDto)
                .collect(Collectors.toList());
        if (orderHistory.isEmpty()) {
            throw new OrderNotFoundException("order history not found for user with ID: " + userId);
        }

        orderHistory.forEach(this::calculateTotalPrice);
        return orderHistory;
    }

    public List<OrderResponseDto> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate.isAfter(endDate)){
            throw new InvalidDateRangeException();
        }
        List<OrderResponseDto> orderBetween= orderRepository.findByOrderDateBetween(startDate,endDate)
                .stream()
                .map(MappingProfile::mapToOrderResponseDto)
                .collect(Collectors.toList());
        orderBetween.forEach(this::calculateTotalPrice);
        return orderBetween;
    }


    private void calculateTotalPrice(OrderResponseDto order) {
        List<OrderItemResponseDto> orderItems = order.getOrderItems();
        if (orderItems != null) {
            BigDecimal totalPrice = orderItems.stream()
                    .map(OrderItemResponseDto::getSubPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalPrice(totalPrice);
        } else {
            order.setTotalPrice(BigDecimal.ZERO);
        }
    }
}
