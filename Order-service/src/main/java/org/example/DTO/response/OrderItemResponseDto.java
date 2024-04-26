package org.example.DTO.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.Entity.OrderItems;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderItemResponseDto {
    private Long OrderItemsId;
    private Long ticketId;
    private Long orderId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subPrice;

}