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

    @PreAuthorize("hasRole('MERCHANT')")
    @PostMapping
    public ResponseEntity<MerchantResponseDTO> registerMerchant(Authentication auth,
                                                                @Valid @RequestBody MerchantCreateDTO dto) {
        Long userId = extractUserId(auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantService.registerMerchant(userId, dto));
    }

    @PreAuthorize("hasRole('MERCHANT') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponseDTO> getMerchant(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @PreAuthorize("hasRole('MERCHANT')")
    @PutMapping("/{id}")
    public ResponseEntity<MerchantResponseDTO> updateMerchant(@PathVariable Long id,
                                                              @Valid @RequestBody MerchantUpdateDTO dto) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }

    // Utility to extract userId from Authentication principal. Adjust to your UserDetails implementation.
    private Long extractUserId(Authentication authentication) {
        return Long.parseLong(authentication.getName());
    }
}
