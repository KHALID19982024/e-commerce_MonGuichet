package org.example.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.DTO.PaymentDTO;
import org.example.DTO.PaymentRequest;
import org.example.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.Constants.PaymentConstant.PAYMENT_URI_REST_API;

@RestController
@RequestMapping(path = PAYMENT_URI_REST_API)
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable("id") Long id) {
        log.info("Retrieving payment with id {}", id);
        return new ResponseEntity<>(
                paymentService.getPayment(id),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/" )
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        log.info("Retrieving all payments");
        return new ResponseEntity<>(
                paymentService.getAllPayments(),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/make-new-payment")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentRequest payment) {

        return new ResponseEntity<>(
                paymentService.createPayment(payment),
                HttpStatus.CREATED
        );
    }
}
