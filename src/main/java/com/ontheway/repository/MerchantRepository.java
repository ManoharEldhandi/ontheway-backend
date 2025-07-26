package com.ontheway.repository;

import com.ontheway.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findByUserUserId(Long userId);
    Optional<Merchant> findByStoreName(String storeName);
}
