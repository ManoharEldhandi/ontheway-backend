package com.ontheway.repository;

import com.ontheway.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    // ✅ Add this method if it doesn't exist
    Optional<Merchant> findByUser_UserId(Long userId);

    // ✅ Alternative query method (if the above doesn't work with your entity structure)
    // @Query("SELECT m FROM Merchant m WHERE m.user.userId = :userId")
    // Optional<Merchant> findByUserId(@Param("userId") Long userId);
}
