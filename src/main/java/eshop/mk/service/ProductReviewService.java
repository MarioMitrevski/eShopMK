package eshop.mk.service;

import eshop.mk.model.ProductReview;

import java.util.List;
import java.util.UUID;

public interface ProductReviewService {

    ProductReview saveProductReview(ProductReview productReview);

    List<ProductReview> findAllByProductId(UUID productId);
}
