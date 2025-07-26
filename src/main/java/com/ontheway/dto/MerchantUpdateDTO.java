package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class MerchantUpdateDTO {
    @JsonProperty("storeName")
    @NotBlank(message = "Store name cannot be blank")
    private String storeName;

    @JsonProperty("address")
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @JsonProperty("etaBufferMins")
    @NotNull(message = "ETA buffer is required")
    @Min(value = 1, message = "ETA buffer must be positive")
    private Integer etaBufferMins;
}
