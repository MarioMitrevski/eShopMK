package eshop.mk.controller;

import eshop.mk.service.ProductImagesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api/Images")

public class ProductImagesController {

    private final ProductImagesService productImagesService;

    public ProductImagesController(ProductImagesService productImagesService) {
        this.productImagesService = productImagesService;
    }

    @PostMapping(path = "product")
    public String uploadProductImages(@RequestParam UUID productId,@RequestHeader String shopName,
            @RequestParam MultipartFile [] productImages){

        productImagesService.uploadProductImages(productImages,productId,shopName);
        return "Uploading Images";
    }

    @PostMapping(path = "shop")
    public String uploadShopImage(@RequestParam UUID userId,@RequestHeader UUID shopId,
                                      @RequestParam MultipartFile shopLogoImage) throws IOException, InterruptedException {

        productImagesService.uploadShopImage(shopLogoImage,userId,shopId);
        return "Uploading Images";
    }
}
