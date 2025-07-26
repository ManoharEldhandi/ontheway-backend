package com.ontheway.service.impl;

import com.ontheway.dto.*;
import com.ontheway.exception.ResourceNotFoundException;
import com.ontheway.model.*;
import com.ontheway.model.enums.OrderStatus;
import com.ontheway.repository.*;
import com.ontheway.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MerchantRepository merchantRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public OrderResponseDTO placeOrder(Long userId, OrderCreateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Merchant merchant = merchantRepository.findById(dto.getMerchantId())
                .orElseThrow(() -> new ResourceNotFoundException("Merchant not found"));

        Order order = Order.builder()
                .user(user)
                .merchant(merchant)
                .orderTime(LocalDateTime.now())
                .pickupTime(dto.getPickupTime())
                .status(OrderStatus.PLACED)
                .totalAmount(0.0) // Set after items processed
                .etaSegment(null)
                .build();

        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;
        for (OrderItemCreateDTO itemDTO : dto.getItems()) {
            MenuItem item = menuItemRepository.findById(itemDTO.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(item)
                    .quantity(itemDTO.getQuantity())
                    .priceEach(item.getPrice())
                    .build();
            items.add(orderItem);
            total += item.getPrice() * itemDTO.getQuantity();
        }
        order.setItems(items);
        order.setTotalAmount(total);

        orderRepository.save(order);
        return toResponseDTO(order);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserUserId(userId)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> getOrdersByMerchant(Long merchantId) {
        return orderRepository.findByMerchantMerchantId(merchantId)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
        return toResponseDTO(order);
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> items = order.getItems().stream().map(oi ->
                OrderItemResponseDTO.builder()
                        .orderItemId(oi.getOrderItemId())
                        .menuItemId(oi.getMenuItem().getMenuItemId())
                        .quantity(oi.getQuantity())
                        .priceEach(oi.getPriceEach())
                        .totalPrice(oi.getQuantity() * oi.getPriceEach())
                        .build()
        ).collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUser().getUserId())
                .merchantId(order.getMerchant().getMerchantId())
                .orderTime(order.getOrderTime())
                .pickupTime(order.getPickupTime())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(items)
                .payment(order.getPayment() != null ? PaymentResponseDTO.builder()
                        .paymentId(order.getPayment().getPaymentId())
                        .orderId(order.getPayment().getOrder().getOrderId())
                        .paymentStatus(order.getPayment().getPaymentStatus())
                        .paymentMethod(order.getPayment().getPaymentMethod())
                        .amount(order.getPayment().getAmount())
                        .paymentTime(order.getPayment().getPaymentTime())
                        .build() : null)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
