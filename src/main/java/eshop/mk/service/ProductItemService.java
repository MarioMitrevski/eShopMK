package eshop.mk.service;

import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductItemCreationDTO;

import java.util.List;

public interface ProductItemService {

    String createProductItems(Product product, List<ProductItemCreationDTO> productItemCreationDTOS);
}
