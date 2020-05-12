package eshop.mk.model.modelDTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShopDTO {

    private UUID shopId;
    private String shopName;
    private String shopDescription;
    @JsonIgnore
    private String shopLogoImage;
    private Long shopCategory;
    private LocalDateTime createdDate;

    @Transient
    private URL shopLogo;

    public ShopDTO(UUID shopId,String shopName,String shopDescription,String shopLogoImage,Long shopCategory,LocalDateTime createdDate){
        this.shopCategory = shopCategory;
        this.shopId =shopId;
        this.shopDescription =shopDescription;
        this.createdDate = createdDate;
        this.shopName = shopName;
        this.shopLogoImage = shopLogoImage;
    }
}
