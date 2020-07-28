package eshop.mk.service;

import eshop.mk.model.Page;
import eshop.mk.model.Shop;
import eshop.mk.model.modelDTOS.ShopCreationDTO;
import eshop.mk.model.modelDTOS.ShopDTO;
import eshop.mk.model.modelDTOS.ShopDetailsDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShopsService {

    UUID createShop(UUID user, ShopCreationDTO shop);

    Optional<Shop> getShop(UUID shopId);

    Page<ShopDTO> getAllShops(int page, int size);

    ShopDetailsDTO getShopDetails(UUID shopId, int page, int size, String sort, String order);
}
