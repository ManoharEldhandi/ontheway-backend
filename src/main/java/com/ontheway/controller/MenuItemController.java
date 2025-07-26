package com.ontheway.controller;

import com.ontheway.dto.*;
import com.ontheway.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PreAuthorize("hasRole('MERCHANT')")
    @PostMapping("/{merchantId}")
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(@PathVariable Long merchantId,
                                                           @Valid @RequestBody MenuItemCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.addMenuItem(merchantId, dto));
    }

    @PreAuthorize("hasRole('MERCHANT')")
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItem(@PathVariable Long id,
                                                              @Valid @RequestBody MenuItemUpdateDTO dto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, dto));
    }

    @PreAuthorize("hasRole('MERCHANT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('MERCHANT') or hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> getMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @PreAuthorize("hasRole('MERCHANT') or hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItemsByMerchant(@PathVariable Long merchantId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByMerchant(merchantId));
    }
}
