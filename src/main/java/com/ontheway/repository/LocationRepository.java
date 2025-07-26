package com.ontheway.repository;

import com.ontheway.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByUserUserId(Long userId);
}
