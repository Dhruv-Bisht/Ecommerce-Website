package com.dhruv.model;

import com.dhruv.domain.PaymentMethod;
import com.dhruv.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentOrder {
    @Id // it means Primary key. because every user must have a uinque Id.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Spring Boot will generate the ID automatically.
    private Long id;

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod; // razorpay or stripe

    private String paymentLinkId;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order> orders = new HashSet<>();



}
