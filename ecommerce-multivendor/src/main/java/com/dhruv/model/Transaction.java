package com.dhruv.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {
    @Id // it means Primary key. because every user must have a uinque Id.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Spring Boot will generate the ID automatically.
    private Long id;

    @ManyToOne
    private User customer;

    @OneToOne
    private Order order;

    @ManyToOne
    private Seller seller;

    private LocalDateTime data = LocalDateTime.now();

}
