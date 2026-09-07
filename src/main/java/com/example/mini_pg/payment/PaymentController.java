package com.example.mini_pg.payment;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse approve(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                   @Valid @RequestBody PaymentRequest request) {
        Payment payment = paymentService.approve(
                idempotencyKey, request.merchantId(), request.orderId(), request.amount());
        return PaymentResponse.from(payment);
    }
}