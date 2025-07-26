package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDTO {
    @JsonProperty("merchantId")
    @NotNull(message = "Merchant ID is required")
    private Long merchantId;

    @JsonProperty("pickupTime")
    @NotNull(message = "Pickup time is required")
    private LocalDateTime pickupTime;

    @JsonProperty("items")
    @NotEmpty(message = "At least one item is required")
    private List<OrderItemCreateDTO> items;

    @JsonProperty("paymentMethod")
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}
