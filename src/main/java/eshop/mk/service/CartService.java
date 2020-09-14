package eshop.mk.service;

import eshop.mk.model.Cart;
import eshop.mk.model.modelDTOS.CartDTO;
import eshop.mk.model.modelRequests.AddCartItemRequest;

public interface CartService {

    Cart findCartByCurrentUser();

    void addToCart(AddCartItemRequest addCartItemRequest);
}
