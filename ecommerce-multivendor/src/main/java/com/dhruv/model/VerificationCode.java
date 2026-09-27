package com.dhruv.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VerificationCode {
    @Id // it means Primary key. because every user must have a uinque Id.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Spring Boot will generate the ID automatically.
    private Long id;

    private String otp;

    private String email;

    @OneToOne
    private User user;

    @OneToOne
    private Seller seller;

}
