package eshop.mk.model.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProductsForMainPageProjection {

     UUID getProductId();
     String getProductName();
     String getProductDescription();
     Double getPrice();
     String getImagePath();
}
