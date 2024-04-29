package org.example.DTO;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Long orderId;
}