package com.ontheway.repository;

import com.ontheway.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMerchantMerchantId(Long merchantId);
    List<MenuItem> findByAvailabilityTrue();
}
