package org.example.DTO.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.Enum.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
//    private String orderNumber;
    private Date orderDate;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
//    @JsonIgnoreProperties("order")
    private List<OrderItemResponseDto> orderItems;
}
