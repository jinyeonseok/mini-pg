package com.example.mini_pg.payment;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment approve(String idempotencyKey, String merchantId, String orderId, long amount) {
        Payment payment = new Payment(idempotencyKey, merchantId, orderId, amount);
        return paymentRepository.save(payment);
    }
}
