package eshop.mk.repository;

import eshop.mk.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewsRepository extends JpaRepository<ProductReview,Long> {
}
