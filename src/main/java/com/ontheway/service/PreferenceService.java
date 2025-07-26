package com.ontheway.service;

import com.ontheway.dto.*;

public interface PreferenceService {
    PreferenceResponseDTO getPreferenceByUserId(Long userId);
    PreferenceResponseDTO updatePreference(Long userId, PreferenceUpdateDTO dto);
}
