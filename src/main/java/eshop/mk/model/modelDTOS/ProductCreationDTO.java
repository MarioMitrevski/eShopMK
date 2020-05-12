package eshop.mk.model.modelDTOS;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ProductCreationDTO {

     UUID shopId;
     UUID userId;
     String productName;
     String productDescription;
     String productSKU;
     Long productCategoryId;
     List<ProductItemCreationDTO> productItemCreationDTOS;
}
