package com.dhruv.repository;

import com.dhruv.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
}
