package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductsRepository extends JpaRepository<Product,Long> {
    Product findByProductId(UUID productID);


    org.springframework.data.domain.Page<ProductForMainPageDTO> findAllProductBy(Pageable pageable);
}
