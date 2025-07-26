package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreateDTO {
    @JsonProperty("orderId")
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @JsonProperty("paymentMethod")
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}
