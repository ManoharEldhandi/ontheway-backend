package com.ontheway.service.impl;

import com.ontheway.dto.*;
import com.ontheway.exception.ResourceNotFoundException;
import com.ontheway.model.*;
import com.ontheway.model.enums.PaymentStatus;
import com.ontheway.repository.*;
import com.ontheway.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public PaymentResponseDTO createPayment(PaymentCreateDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Payment payment = Payment.builder()
                .order(order)
                .paymentMethod(dto.getPaymentMethod())
                .amount(order.getTotalAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .paymentTime(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);

        return toResponseDTO(payment);
    }

    @Override
    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderOrderId(orderId)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }

    @Transactional
    @Override
    public void updatePaymentStatus(Long paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setPaymentStatus(PaymentStatus.valueOf(status));
        paymentRepository.save(payment);
    }

    private PaymentResponseDTO toResponseDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrder().getOrderId())
                .paymentStatus(payment.getPaymentStatus())
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .paymentTime(payment.getPaymentTime())
                .build();
    }
}
