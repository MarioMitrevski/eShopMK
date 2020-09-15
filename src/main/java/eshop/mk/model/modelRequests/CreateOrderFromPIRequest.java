package eshop.mk.model.modelRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderFromPIRequest {

    UUID productItemId;
    Integer orderedQuantity;


}
