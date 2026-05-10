package com.phong.it.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Coupon extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã giảm giá không được để trống")
    @Column(unique = true, nullable = false)
    private String code; // Ví dụ: "XUAN2026"

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @NotNull
    @Column(name = "discount_value")
    private BigDecimal discountValue; // Giá trị giảm (Ví dụ: 50000 hoặc 15)

    @Column(name = "min_order_value")
    private BigDecimal minOrderValue; // Giá trị đơn hàng tối thiểu để áp dụng

    @Column(name = "max_discount_value")
    private BigDecimal maxDiscountValue; // Số tiền giảm tối đa (nếu dùng PERCENTAGE)

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "usage_limit")
    private Integer usageLimit; // Tổng số lần mã này có thể được sử dụng

    @Column(name = "used_count")
    private Integer usedCount = 0; // Số lần đã thực tế sử dụng

    @Column(name = "is_active")
    private Boolean isActive = true;
}
