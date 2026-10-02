package com.dhruv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dhruv.model.User;

// In User the ID is long so same data type
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
