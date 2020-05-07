package eshop.mk.service;


import eshop.mk.model.Product;
import eshop.mk.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface ProductImagesService {

    String uploadProductImages(MultipartFile[] productImagesList, UUID productId, String shopName);
    ProductImage uploadOneProductImage(MultipartFile image, Product product, String shopName) throws IOException, InterruptedException;


    URL downloadProductFirstImage(String imagePath);
    List<ProductImage> findProductImagesForProducts(String imagePathEndsWith, List<UUID> ids);



}


