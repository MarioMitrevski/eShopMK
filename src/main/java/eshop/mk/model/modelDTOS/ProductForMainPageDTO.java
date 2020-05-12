package eshop.mk.model.modelDTOS;

import lombok.Data;
import java.net.URL;
import java.util.UUID;

@Data
public class ProductForMainPageDTO {

    private UUID productId;
    private String productName;
    private String productDescription;
    private Double price;
    private URL imageURL;
    private UUID shopId;
    public ProductForMainPageDTO(UUID productId,String productName,String productDescription,Double price,URL imageURL,UUID shopId){
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        this.imageURL = imageURL;
        this.shopId = shopId;
    }
}
