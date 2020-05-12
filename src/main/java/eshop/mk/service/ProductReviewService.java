package eshop.mk.service;

import eshop.mk.model.ProductReview;
import eshop.mk.model.modelDTOS.ProductReviewDTO;

import java.util.List;
import java.util.UUID;

public interface ProductReviewService {

    ProductReview saveProductReview(ProductReview productReview);

    List<ProductReviewDTO> findAllByProductId(UUID productId);
}
