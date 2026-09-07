package com.example.mini_pg.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
        @NotBlank String merchantId,
        @NotBlank String orderId,
        @Positive long amount
) {
}
