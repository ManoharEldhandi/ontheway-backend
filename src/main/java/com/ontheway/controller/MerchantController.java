package com.ontheway.controller;

import com.ontheway.dto.*;
import com.ontheway.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    /**
     * Register a merchant for the authenticated user.
     */
    @PreAuthorize("hasRole('MERCHANT')")
    @PostMapping
    public ResponseEntity<MerchantResponseDTO> registerMerchant(
            Authentication auth,
            @Valid @RequestBody MerchantCreateDTO dto) {

        String email = auth.getName(); // JWT subject (email)
        MerchantResponseDTO response = merchantService.registerMerchant(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get merchant by ID (accessible to MERCHANT and ADMIN).
     */
    @PreAuthorize("hasRole('MERCHANT') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponseDTO> getMerchant(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    /**
     * Update a merchant by ID (MERCHANT only).
     */
    @PreAuthorize("hasRole('MERCHANT')")
    @PutMapping("/{id}")
    public ResponseEntity<MerchantResponseDTO> updateMerchant(
            @PathVariable("id") Long id,
            @Valid @RequestBody MerchantUpdateDTO dto) {

        return ResponseEntity.ok(merchantService.updateMerchant(id, dto));
    }

    /**
     * Delete a merchant by ID (ADMIN only).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable("id") Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
}
