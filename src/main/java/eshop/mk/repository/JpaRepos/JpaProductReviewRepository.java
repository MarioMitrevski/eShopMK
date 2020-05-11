package eshop.mk.repository.JpaRepos;


import eshop.mk.model.Product;
import eshop.mk.model.ProductReview;
import eshop.mk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaProductReviewRepository extends JpaRepository<ProductReview,Long> {

    ProductReview findByUserIdAndProductId(UUID user, UUID product);

    List<ProductReview> findAllByProductId(UUID productId);
}
