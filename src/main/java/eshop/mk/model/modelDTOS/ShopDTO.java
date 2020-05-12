package eshop.mk.model.modelDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ShopDTO {

    private UUID shopId;
    private String shopName;
    private String shopDescription;
    private String shopLogoImage;
    private Long shopCategory;
    private LocalDateTime createdDate;

    public ShopDTO(UUID shopId,String shopName,String shopDescription,String shopLogoImage,Long shopCategory,LocalDateTime createdDate){
        this.shopCategory = shopCategory;
        this.shopId =shopId;
        this.shopDescription =shopDescription;
        this.createdDate = createdDate;
        this.shopName = shopName;
        this.shopLogoImage = shopLogoImage;
    }
}
