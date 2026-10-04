package com.dhruv.repository;

// In User the ID is long so same data type
import com.dhruv.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"addresses"})
    User findByEmail(String email);
}