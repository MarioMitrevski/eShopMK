package eshop.mk.model.modelDTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShopDTO {

    private UUID shopId;
    private String shopName;
    private String shopDescription;
    private Long shopCategory;
    private LocalDateTime createdDate;

    @JsonIgnore
    private String shopLogoImage;

    @Transient
    private URL shopLogo;

    public ShopDTO(UUID shopId,
                   String shopName,
                   String shopDescription,
                   String shopLogoImage,
                   Long shopCategory,
                   LocalDateTime createdDate) {
        this.shopCategory = shopCategory;
        this.shopId = shopId;
        this.shopDescription = shopDescription;
        this.createdDate = createdDate;
        this.shopName = shopName;
        this.shopLogoImage = shopLogoImage;
    }
}
