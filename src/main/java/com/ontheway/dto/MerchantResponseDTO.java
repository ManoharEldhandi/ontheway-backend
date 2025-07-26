package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ontheway.model.enums.StoreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponseDTO {
    @JsonProperty("merchantId")
    private Long merchantId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("storeType")
    private StoreType storeType;

    @JsonProperty("address")
    private String address;

    @JsonProperty("etaBufferMins")
    private Integer etaBufferMins;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
