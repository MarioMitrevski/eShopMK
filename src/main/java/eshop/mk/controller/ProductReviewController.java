package eshop.mk.controller;

import eshop.mk.model.ProductReview;
import eshop.mk.service.ProductReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/productReviews")

public class ProductReviewController {

    private final ProductReviewService productReviewService;


    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping(path = "/createProductReview")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReview createProductReview(@RequestBody ProductReview productReview){

        return productReviewService.saveProductReview(productReview);
    }
}
