package eshop.mk.model.modelRequests;


import java.util.UUID;

public class AddCartItemRequest {

    private UUID productItemId;
    private Integer cartItemQuantity;

    public AddCartItemRequest(UUID productItemId, Integer cartItemQuantity) {
        this.productItemId = productItemId;
        this.cartItemQuantity = cartItemQuantity;
    }

    public UUID getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(UUID productItemId) {
        this.productItemId = productItemId;
    }

    public Integer getCartItemQuantity() {
        return cartItemQuantity;
    }

    public void setCartItemQuantity(Integer cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }
}
