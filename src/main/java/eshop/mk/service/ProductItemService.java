package eshop.mk.service;

import eshop.mk.model.Product;
import eshop.mk.model.ProductItem;
import eshop.mk.model.modelDTOS.ProductItemCreationDTO;

import java.util.List;
import java.util.UUID;

public interface ProductItemService {

    String createProductItems(Product product, List<ProductItemCreationDTO> productItemCreationDTOS);

    List<ProductItem> getProductItems(UUID productId);
}
