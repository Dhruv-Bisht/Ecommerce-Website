package com.dhruv.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerReport {
    @Id // it means Primary key. because every user must have a uinque Id.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Spring Boot will generate the ID automatically.
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings = 0L;

    private Long totalSales = 0L;

    private Long totalRefunds = 0L;

    private Long totalTax = 0L;

    private Long netEarnings = 0L;

    private Integer totalOrders = 0;

    private Integer canceledOrders = 0;

    private Integer totalTransactions = 0;

}
