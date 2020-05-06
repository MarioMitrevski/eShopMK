package eshop.mk.service;

import eshop.mk.model.Shop;
import eshop.mk.model.modelDTOS.ShopCreationDTO;

import java.util.List;
import java.util.UUID;

public interface ShopsService {
    String createShop(UUID user, ShopCreationDTO shop);
    Shop getShop(UUID shopId);

    List<Shop> getAllShops();
}
