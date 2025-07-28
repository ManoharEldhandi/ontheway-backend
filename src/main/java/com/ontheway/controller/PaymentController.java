package com.ontheway.controller;

import com.ontheway.dto.*;
import com.ontheway.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Create a new payment for the specified order.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(dto));
    }

    /**
     * Get payment details for a given order ID.
     */
    @PreAuthorize("hasAnyRole('USER', 'MERCHANT', 'ADMIN')")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

    /**
     * Update payment status (e.g. SUCCESS, FAILED, PENDING).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable("paymentId") Long paymentId,
                                                 @RequestParam("status") String status) {
        paymentService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.noContent().build();
    }
}
