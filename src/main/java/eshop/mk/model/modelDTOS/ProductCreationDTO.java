package eshop.mk.model.modelDTOS;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
public class ProductCreationDTO {

     UUID shopId;

     UUID userId;

     @NotBlank
     String productName;

     String productDescription;

     @NotBlank
     String productSKU;
     Long productCategoryId;
     List<ProductItemCreationDTO> productItemCreationDTOS;
}
