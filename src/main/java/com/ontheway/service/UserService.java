package com.ontheway.service;

import com.ontheway.dto.*;

public interface UserService {
    UserResponseDTO registerUser(UserCreateDTO dto);
    UserResponseDTO getUserById(Long userId);
    UserResponseDTO updateUser(Long userId, UserUpdateDTO dto);
    void deleteUser(Long userId);
    UserResponseDTO getUserByEmail(String email);
}
