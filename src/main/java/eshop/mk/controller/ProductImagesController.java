package eshop.mk.controller;

import eshop.mk.service.ProductImagesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(path="/api/productsImages")

public class ProductImagesController {

    private final ProductImagesService productImagesService;

    public ProductImagesController(ProductImagesService productImagesService) {
        this.productImagesService = productImagesService;
    }

    @PostMapping
    public String uploadProductImages(@RequestParam UUID productId,@RequestHeader String shopName,
            @RequestParam MultipartFile [] productImages){

        productImagesService.uploadProductImages(productImages,productId,shopName);
        return "Uploading Images";
    }
}
