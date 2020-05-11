package eshop.mk.service;


import eshop.mk.model.ProductImage;
import eshop.mk.model.projections.ProductIdProjection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface ProductImagesService {

    String uploadProductImages(MultipartFile[] productImagesList, UUID productId, String shopName);
    ProductImage uploadOneProductImage(MultipartFile image, ProductIdProjection product, String shopName) throws IOException, InterruptedException;


    URL downloadProductImage(String imagePath);

    List<URL> getProductImages(List<String> productImagesPaths);
}


