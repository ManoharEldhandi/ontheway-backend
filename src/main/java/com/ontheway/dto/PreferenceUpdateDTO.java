package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ontheway.model.enums.VegNonVeg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceUpdateDTO {
    @JsonProperty("vegNonVeg")
    private VegNonVeg vegNonVeg;

    @JsonProperty("favoriteCuisine")
    private String favoriteCuisine;
}
