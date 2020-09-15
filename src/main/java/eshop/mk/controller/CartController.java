package eshop.mk.controller;


import eshop.mk.model.Cart;
import eshop.mk.model.modelDTOS.CartDTO;
import eshop.mk.model.modelRequests.AddCartItemRequest;
import eshop.mk.service.CartService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://192.168.100.16:3000")
@RequestMapping(path = "/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping(path = "")
    public Cart getCartFromUser(){
        return this.cartService.findCartByCurrentUser();
    }


    @PutMapping(path="addProductItem")
    public void addToCart(@RequestBody AddCartItemRequest cartItemRequest){
        this.cartService.addToCart(cartItemRequest);
    }


}
