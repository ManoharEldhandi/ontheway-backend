package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    @JsonProperty("orderItemId")
    private Long orderItemId;

    @JsonProperty("menuItemId")
    private Long menuItemId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("priceEach")
    private Double priceEach;

    @JsonProperty("totalPrice")
    private Double totalPrice;
}
