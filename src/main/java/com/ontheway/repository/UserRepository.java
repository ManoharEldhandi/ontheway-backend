package com.ontheway.repository;

import com.ontheway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // (optional to remove)
    Optional<User> findByEmailIgnoreCase(String email); // ✅ case-insensitive lookup
    boolean existsByEmail(String email);
}
