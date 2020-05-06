package eshop.mk.repository;


import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eshop.mk.model.*;

import java.util.UUID;



public interface ProductsRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(UUID productID);

    org.springframework.data.domain.Page<ProductForMainPageDTO> findAllProductForMainBy(Pageable pageable);


}