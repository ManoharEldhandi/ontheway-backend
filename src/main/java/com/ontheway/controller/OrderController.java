package com.ontheway.controller;

import com.ontheway.dto.*;
import com.ontheway.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(Authentication auth,
                                                       @Valid @RequestBody OrderCreateDTO dto) {
        Long userId = extractUserId(auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(userId, dto));
    }

    @PreAuthorize("hasAnyRole('USER', 'MERCHANT', 'ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersForUser(Authentication auth) {
        Long userId = extractUserId(auth);
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping("/merchant")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersForMerchant(Authentication auth) {
        Long merchantId = extractMerchantId(auth); // Implement extraction method based on your security context
        return ResponseEntity.ok(orderService.getOrdersByMerchant(merchantId));
    }

    @PreAuthorize("hasRole('MERCHANT')")
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long orderId,
                                                              @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    private Long extractUserId(Authentication authentication) {
        // Implement based on your UserDetails principal in Authentication object
        return Long.parseLong(authentication.getName());
    }

    private Long extractMerchantId(Authentication authentication) {
        // Implementation depends on how you store merchantId in UserDetails or SecurityContext
        // For example, you can query merchantRepository with userId
        // Here, you need to inject that logic or service, omitted for brevity
        throw new UnsupportedOperationException("Please implement merchantId extraction logic");
    }
}
