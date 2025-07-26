package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ontheway.model.enums.VegNonVeg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceResponseDTO {
    @JsonProperty("preferenceId")
    private Long preferenceId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("vegNonVeg")
    private VegNonVeg vegNonVeg;

    @JsonProperty("favoriteCuisine")
    private String favoriteCuisine;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
