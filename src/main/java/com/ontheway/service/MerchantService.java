package com.ontheway.service;

import com.ontheway.dto.*;

public interface MerchantService {
    MerchantResponseDTO registerMerchant(Long userId, MerchantCreateDTO dto);
    MerchantResponseDTO getMerchantById(Long merchantId);
    MerchantResponseDTO updateMerchant(Long merchantId, MerchantUpdateDTO dto);
    void deleteMerchant(Long merchantId);
}
