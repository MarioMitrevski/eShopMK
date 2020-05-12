package eshop.mk.service;


import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductDTO;
import eshop.mk.model.modelDTOS.ProductDetailsDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.projections.ProductsForMainPageProjection;

import java.util.List;
import java.util.UUID;

public interface ProductsService {



    UUID createProduct(ProductCreationDTO productCreationDTO);


   Page<ProductForMainPageDTO> getProducts(int page, int size, String sort, String order, Long categoryId);


    ProductDetailsDTO getProduct(UUID productId);

    Page<ProductForMainPageDTO> getProductsFromShop(int page, int size, String sort, String order, UUID shopId);

    Page<ProductForMainPageDTO> getProductsFromShopByCategory(int page, int size, String sort,String order, Long categoryId,UUID shopId);

}
