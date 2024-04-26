package org.example.Service;

import org.example.DTO.PaymentDTO;
import org.example.DTO.PaymentRequest;

import java.util.List;

public interface PaymentService {

    PaymentDTO getPayment(Long id);
    List<PaymentDTO> getAllPayments();
    PaymentDTO createPayment(PaymentRequest payment);
}
