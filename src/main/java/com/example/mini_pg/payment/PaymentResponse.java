package com.example.mini_pg.payment;

public record PaymentResponse(Long id, PaymentStatus status, long amount) {
    static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getStatus(), payment.getAmount());
    }
}