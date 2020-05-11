package eshop.mk.model.modelDTOS;

import eshop.mk.model.ProductItem;
import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDetailsDTO {
    private UUID productId;
    private String productName;
    private String productDescription;
    private Double price;
    private List<String> imagesUrls;
    private List<ProductItem> productItems;

    public ProductDetailsDTO(UUID productId, String productName,String productDescription, Double price,List<String> imagesUrls,List<ProductItem> productItems){

        this.productName = productName;
        this.imagesUrls = imagesUrls;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        this.productItems = productItems;
    }
}
