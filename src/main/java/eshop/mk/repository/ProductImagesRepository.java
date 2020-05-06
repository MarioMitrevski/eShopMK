package eshop.mk.repository;

import eshop.mk.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImage,Integer> {

}
