package eshop.mk.service;


import eshop.mk.model.Page;
import eshop.mk.model.Product;


public interface ProductsService {










    void createProduct(Long shopId,
                        String productName,
                        String type,
                        String productDescription,
                        Integer quantity);


}
