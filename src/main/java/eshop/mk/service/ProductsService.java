package eshop.mk.service;


import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.modelDTOS.ProductItemCreationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductsService {



    String createProduct(ProductCreationDTO productCreationDTO);


    Page<ProductForMainPageDTO> getProductsByCategory(int page, int size, String sort, Long categoryId);

}
