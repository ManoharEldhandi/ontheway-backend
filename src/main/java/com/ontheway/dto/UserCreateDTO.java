package com.ontheway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ontheway.model.enums.UserRole;
import jakarta.validation.constraints.Email;
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
public class UserCreateDTO {
    @JsonProperty("email")
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;

    @JsonProperty("name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("role")
    @NotNull(message = "Role is required")
    private UserRole role;
}
