package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
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
public class MenuItemCreateDTO {
    @JsonProperty("name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private Double price;

    @JsonProperty("availability")
    @NotNull(message = "Availability is required")
    private Boolean availability;
}
