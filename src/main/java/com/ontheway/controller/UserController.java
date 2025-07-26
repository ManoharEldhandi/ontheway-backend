package com.ontheway.controller;

import com.ontheway.dto.*;
import com.ontheway.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Get user profile by user ID (requires id parameter)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MERCHANT')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(
            @Parameter(description = "ID of the user to retrieve", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Get current logged-in user profile using authentication token, no id parameter needed
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        UserResponseDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    // Update user profile by user ID
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MERCHANT')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // Delete user by ID, only admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
