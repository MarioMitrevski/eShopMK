package eshop.mk.model.modelDTOS;

import eshop.mk.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    UUID cartId;
    Set<CartItem> cartItemSet;
    private Double subTotal;
    private Double discount;
    private Double total;

}
