package com.ontheway.service;

import com.ontheway.dto.*;

import java.util.List;

public interface LocationService {
    LocationResponseDTO saveLocation(Long userId, LocationCreateDTO dto);
    List<LocationResponseDTO> getUserLocations(Long userId);
}
