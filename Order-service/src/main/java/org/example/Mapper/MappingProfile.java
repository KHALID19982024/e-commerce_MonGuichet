package org.example.Mapper;

import org.example.DTO.request.OrderItemRequest;
import org.example.DTO.request.OrderRequest;
import org.example.DTO.response.OrderItemResponseDto;
import org.example.DTO.response.OrderResponseDto;
import org.example.Entity.Order;
import org.example.Entity.OrderItems;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();



    static {
        modelMapper.addMappings(new PropertyMap<OrderItems, OrderItemResponseDto>() {
            @Override
            protected void configure() {
                // Map RentalItem's Rental's RentalId to RentalItemResponseDto's RentalId
                map().setOrderId(source.getOrder().getOrderId());
            }
        });

        modelMapper.addMappings(new PropertyMap<OrderItemRequest, OrderItems>() {
            @Override
            protected void configure() {
                // Map productId from RentalItemRequestDto to RentalItem
                map().setTicketId(source.getTicketId());
            }
        });
    }



    public static Order mapToOrderEntity(OrderRequest orderRequest){
        return modelMapper.map(orderRequest, Order.class);
    }

    public static OrderResponseDto mapToOrderResponseDto(Order order){
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public static OrderItems mapToOrderItemsEntity(OrderItemRequest orderItemRequest){
        return modelMapper.map(orderItemRequest, OrderItems.class);
    }
    public static OrderItemResponseDto mapToOrderItemResponseDto(OrderItems orderItems){
        return modelMapper.map(orderItems, OrderItemResponseDto.class);
    }
}
