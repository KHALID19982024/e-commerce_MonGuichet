package org.example.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private Long paymentId;
    private Long customerId;
    private Long orderId;
    private LocalDateTime createAt;
}
