package eshop.mk.model.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProductsForMainPageProjection {

     UUID getProductId();
     Double getPrice();
     String getProductName();
     LocalDateTime getCreatedDate();
}
