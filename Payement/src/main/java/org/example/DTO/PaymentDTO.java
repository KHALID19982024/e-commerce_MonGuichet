package org.example.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private Long customerId;
    private Long orderId;
    private LocalDateTime createAt;
}