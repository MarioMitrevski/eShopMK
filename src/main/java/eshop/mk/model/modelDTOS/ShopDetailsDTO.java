package eshop.mk.model.modelDTOS;

import eshop.mk.model.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShopDetailsDTO {

    private UUID shopId;
    private String shopName;
    private String shopDescription;
    private LocalDateTime createdDate;
    private Long categoryId;
    private String shopLogoImage; //SMENI VO URL
    private Page<ProductForMainPageDTO> products;
}
