package org.example.Service;

import lombok.AllArgsConstructor;
import org.example.DTO.PaymentDTO;
import org.example.DTO.PaymentRequest;
import org.example.Entity.PaymentEntity;
import org.example.Mapper.PaymentMapper;
import org.example.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.Constants.PaymentConstant.PAYMENT_NOT_FOUND_EXCEPTION;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
//    private final OrderClient orderClient;
//    private final RabbitMQProducer rabbitMQProducer;

    @Override
    public PaymentDTO getPayment(Long id) {
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(PAYMENT_NOT_FOUND_EXCEPTION));

        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<PaymentEntity> paymentList = paymentRepository.findAll();
        return paymentMapper.toListDTO(paymentList);
    }

    @Override
    public PaymentDTO createPayment(PaymentRequest payment) {

        // find order
//        orderClient.getOrder(payment.getOrderId());

        // add new payment
        PaymentEntity paymentEntity = paymentRepository.save(PaymentEntity.builder()
                .customerId(payment.getCustomerId())
                .orderId(payment.getOrderId())
                .createAt(LocalDateTime.now()).build());

        // create notification request
//        NotificationRequest notificationRequest = NotificationRequest.builder()
//                .customerId(payment.getCustomerId())
//                .customerName(payment.getCustomerName())
//                .customerEmail(payment.getCustomerEmail())
//                .sender("nanodev")
//                .message("Your payment passed successfully. Thank you")
//                .build();
//
//        // publishing notification to rabbitmq
//        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);

        return paymentMapper.toDTO(paymentEntity);
    }
}