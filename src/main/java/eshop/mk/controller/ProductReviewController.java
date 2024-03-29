package eshop.mk.controller;

import eshop.mk.model.modelDTOS.ProductReviewCreationDTO;
import eshop.mk.service.ProductReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.100.16:3000")
@RequestMapping(path = "/api/productReviews")

public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping(path = "/createProductReview")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProductReview(@RequestBody ProductReviewCreationDTO productReview) {
        productReviewService.saveProductReview(productReview);
    }
}
