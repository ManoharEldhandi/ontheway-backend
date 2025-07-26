package com.ontheway.service.impl;

import com.ontheway.dto.*;
import com.ontheway.exception.ResourceNotFoundException;
import com.ontheway.model.*;
import com.ontheway.repository.*;
import com.ontheway.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public MerchantResponseDTO registerMerchant(Long userId, MerchantCreateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Merchant merchant = Merchant.builder()
                .user(user)
                .storeName(dto.getStoreName())
                .storeType(dto.getStoreType())
                .address(dto.getAddress())
                .etaBufferMins(dto.getEtaBufferMins())
                .build();
        merchantRepository.save(merchant);
        return toResponseDTO(merchant);
    }

    @Override
    public MerchantResponseDTO getMerchantById(Long merchantId) {
        return merchantRepository.findById(merchantId)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant not found"));
    }

    @Transactional
    @Override
    public MerchantResponseDTO updateMerchant(Long merchantId, MerchantUpdateDTO dto) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant not found"));
        merchant.setStoreName(dto.getStoreName());
        merchant.setAddress(dto.getAddress());
        merchant.setEtaBufferMins(dto.getEtaBufferMins());
        merchantRepository.save(merchant);
        return toResponseDTO(merchant);
    }

    @Transactional
    @Override
    public void deleteMerchant(Long merchantId) {
        if (!merchantRepository.existsById(merchantId)) {
            throw new ResourceNotFoundException("Merchant not found");
        }
        merchantRepository.deleteById(merchantId);
    }

    private MerchantResponseDTO toResponseDTO(Merchant merchant) {
        return MerchantResponseDTO.builder()
                .merchantId(merchant.getMerchantId())
                .userId(merchant.getUser().getUserId())
                .storeName(merchant.getStoreName())
                .storeType(merchant.getStoreType())
                .address(merchant.getAddress())
                .etaBufferMins(merchant.getEtaBufferMins())
                .createdAt(merchant.getCreatedAt())
                .updatedAt(merchant.getUpdatedAt())
                .build();
    }
}
