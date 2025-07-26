package com.ontheway.repository;

import com.ontheway.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Optional<Preference> findByUserUserId(Long userId);
}
