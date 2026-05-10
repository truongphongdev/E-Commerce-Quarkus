package com.phong.it.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Payment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "transaction_id", unique = true)
    private String transactionId; // Chính là vnp_TxnRef gửi sang VNPay

    @Column(name = "vnp_transaction_no")
    private String vnpTransactionNo; // Mã giao dịch do VNPay trả về

    private BigDecimal amount;

    @Column(name = "bank_code")
    private String bankCode; // Mã ngân hàng (ví dụ: NCB, VCB)

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "vnp_response_code")
    private String vnpResponseCode; // Mã phản hồi (00 là thành công)

    @Column(name = "payment_info")
    private String paymentInfo; // Thông tin nội dung thanh toán

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}