package com.ontheway.service;

import com.ontheway.dto.*;

import java.util.List;

public interface MenuItemService {
    MenuItemResponseDTO addMenuItem(Long merchantId, MenuItemCreateDTO dto);
    MenuItemResponseDTO updateMenuItem(Long menuItemId, MenuItemUpdateDTO dto);
    void deleteMenuItem(Long menuItemId);
    MenuItemResponseDTO getMenuItemById(Long menuItemId);
    List<MenuItemResponseDTO> getMenuItemsByMerchant(Long merchantId);
}
