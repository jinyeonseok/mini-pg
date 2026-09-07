package com.example.mini_pg.payment;

public enum PaymentStatus {
    READY,             // 결제 생성됨. 카드사 응답 대기
    APPROVED,          // 카드사 승인. 결제 완료
    FAILED,            // 카드사 거절
    PARTIAL_CANCELED,  // 일부 취소됨 (canceledAmount < amount)
    CANCELED,          // 전부 취소됨 (canceledAmount == amount)
    UNKNOWN            // 카드사 응답 없음(타임아웃). 승인됐는지 불명
}