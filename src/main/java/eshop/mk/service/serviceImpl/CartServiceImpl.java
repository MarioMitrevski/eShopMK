package eshop.mk.service.serviceImpl;


import eshop.mk.exceptions.CartNotFoundException;
import eshop.mk.exceptions.ProductItemNotFoundException;
import eshop.mk.exceptions.UserNotFoundException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.CartDTO;
import eshop.mk.model.modelRequests.AddCartItemRequest;
import eshop.mk.repository.JpaRepos.*;
import eshop.mk.service.CartService;
import eshop.mk.service.ProductImagesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.*;


@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductItemRepository productItemRepository;
    private final JpaProductsRepository jpaProductsRepository;
    private final ProductImagesService productImagesService;

    public CartServiceImpl(CartRepository cartRepository,
                           UsersRepository usersRepository,
                           CartItemRepository cartItemRepository,
                           ProductItemRepository productItemRepository,
                           JpaProductsRepository jpaProductsRepository,
                           ProductImagesService productImagesService){
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
        this.cartItemRepository = cartItemRepository;
        this.productItemRepository = productItemRepository;
        this.jpaProductsRepository = jpaProductsRepository;
        this.productImagesService = productImagesService;
    }

    @Override
    public Cart findCartByCurrentUser() {

        String currentUserName = this.getCurrentLoggedInUser();
        User user = usersRepository.findUserByUsername(currentUserName).orElseThrow(UserNotFoundException::new);
        if(user.getCart()!=null){
            return user.getCart();
        }else {
            throw new CartNotFoundException();
        }
    }


    @Override
    @Transactional
    public void addToCart(AddCartItemRequest addCartItemRequest) {
        String currentUserName = this.getCurrentLoggedInUser();
        ProductItem pi = productItemRepository.findByProductItemId(addCartItemRequest.getProductItemId()).orElseThrow(ProductItemNotFoundException::new);
        Product p = jpaProductsRepository.findByProductId(pi.getProduct());
        URL imageUrl = productImagesService.downloadProductImage(p.getProductImages().get(0).getImagePath());
        List<CartItem> cartItems = new LinkedList<>();
        CartItem cartItem = new CartItem(UUID.randomUUID(),p.getProductName(),imageUrl,null,pi,addCartItemRequest.getCartItemQuantity());
        User user = usersRepository.findUserByUsername(currentUserName).orElseThrow(UserNotFoundException::new);
        Cart cart = user.getCart();
        if(cart != null){
            cartItems = cart.getCartItems();
            cartItems.add(cartItem);
            Double totalPrice = cartItems.stream().mapToDouble(it->it.getProductItem().getPrice()).sum();
            cart.setSubTotal(totalPrice);
            cart.setDiscount(0.0);
            cart.setTotal(0.0);
            Cart c1 = cartRepository.save(cart);
            cartItem.setCart(c1);
            cartItemRepository.save(cartItem);
            user.setCart(c1);
            usersRepository.save(user);
        }else {
            cartItems.add(cartItem);
            cart = new Cart();
            cart.setTotal(pi.getPrice());
            cart.setDiscount(0.0);
            cart.setSubTotal(pi.getPrice());
            Cart c = cartRepository.save(cart);
            cartItem.setCart(c);
            cartItemRepository.save(cartItem);
            user.setCart(c);
            usersRepository.save(user);
        }

    }


    public String getCurrentLoggedInUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
