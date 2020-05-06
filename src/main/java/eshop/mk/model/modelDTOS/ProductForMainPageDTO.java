package eshop.mk.model.modelDTOS;

import eshop.mk.model.Shop;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class ProductForMainPageDTO {

    private UUID productId;
    private Double price;
    private String productName;
    public ProductForMainPageDTO(UUID productId, Double price, String productName){
        this.price = price;
        this.productId = productId;
        this.productName = productName;
    }
}
