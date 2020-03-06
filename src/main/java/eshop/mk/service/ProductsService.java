package eshop.mk.service;


import eshop.mk.model.Page;
import eshop.mk.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductsService {



    void createProduct(UUID shopId,
                       String productName,
                       String type,
                       String productDescription,
                       Integer quantity);

    Page<Product> getAllProducts(int page, int size);

    Page<Product> getProductsByCategory(int page, int size, List<String> categoryList);

}
