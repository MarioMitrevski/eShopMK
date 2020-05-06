package eshop.mk.service;

import eshop.mk.model.Product;
import eshop.mk.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ProductImagesService {

    String uploadProductImages(MultipartFile[] productImagesList, UUID productId, String shopName);
    ProductImage uploadOneProductImage(MultipartFile image, Product product, String shopName) throws IOException, InterruptedException;


}


