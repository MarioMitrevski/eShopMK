package eshop.mk.repository.JpaRepos;

import eshop.mk.model.ProductImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface ProductImagesRepository extends JpaRepository<ProductImage,Integer> {


    @Query("select pi from ProductImage pi where pi.product = :productId")
    List<ProductImage> findAllProductImages(UUID productId);

}
