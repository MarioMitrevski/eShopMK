package eshop.mk.repository.repositoryImpl;

import eshop.mk.model.Product;
import eshop.mk.model.ProductReview;
import eshop.mk.model.User;
import eshop.mk.model.modelDTOS.ProductReviewDTO;
import eshop.mk.repository.JpaRepos.JpaProductReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductReviewRepositoryImpl {


    private final JpaProductReviewRepository productReviewRepository;

    public ProductReviewRepositoryImpl(JpaProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public ProductReview saveProductReview(ProductReview productReview){
        return productReviewRepository.save(productReview);
    }

    public ProductReview findProductReviewByUserAndProduct(UUID user, UUID product){
        return productReviewRepository.findByUserIdAndProductId(user,product);
    }


    public List<ProductReviewDTO> findAllByProductId(UUID product){
        return productReviewRepository.findAllByProductId(product);
    }



}
