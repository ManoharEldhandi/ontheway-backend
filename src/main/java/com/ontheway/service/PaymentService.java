package com.ontheway.service;

import com.ontheway.dto.*;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentCreateDTO dto);
    PaymentResponseDTO getPaymentByOrderId(Long orderId);
    void updatePaymentStatus(Long paymentId, String status);
}
