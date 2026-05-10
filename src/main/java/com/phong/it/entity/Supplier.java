package com.phong.it.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Supplier extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên nhà cung cấp không được để trống")
    @Column(nullable = false)
    private String name;

    @Column(name = "contact_name")
    private String contactName;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    // Một nhà cung cấp có thể cung cấp nhiều sản phẩm
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}