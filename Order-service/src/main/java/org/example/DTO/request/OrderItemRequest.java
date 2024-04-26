package org.example.DTO.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderItemRequest {
    private Long OrderItemsId;
//    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
//    @Max(value = 10_000, message = "Quantity must be less than or equal to 10 000")
//    @NotNull(message = "Quantity is required")
    private Integer quantity;
    private Long ticketId;
    private Long orderId;
    private BigDecimal price;


//    @NotNull(message = "Ticket ID is required")
}