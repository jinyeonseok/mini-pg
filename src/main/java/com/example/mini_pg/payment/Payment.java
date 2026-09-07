package com.example.mini_pg.payment;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment", uniqueConstraints = @UniqueConstraint(columnNames = "idempotency_key"))
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto increment PK
    private Long id;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @Column(nullable = false)
    private String merchantId;      // 가맹점

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private long amount;            // 승인 금액. 원 단위 long

    @Column(nullable = false)
    private long canceledAmount;    // 취소 누적

    @Enumerated(EnumType.STRING)    // DB에 숫자 말고 문자열로 저장
    @Column(nullable = false)
    private PaymentStatus status;

    @Version                        // 동시 수정 감지용. 나중에 취소에서 씀
    private Long version;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Payment() {
    }

    public Payment(String idempotencyKey, String merchantId, String orderId, long amount) {
        this.idempotencyKey = idempotencyKey;
        this.merchantId = merchantId;
        this.orderId = orderId;
        this.amount = amount;
        this.canceledAmount = 0;
        this.status = PaymentStatus.READY;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getAmount() {
        return amount;
    }

    public long getCanceledAmount() {
        return canceledAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
