package eshop.mk.service.serviceImpl;


import eshop.mk.exceptions.CartNotFoundException;
import eshop.mk.exceptions.ProductItemNotFoundException;
import eshop.mk.exceptions.UserNotFoundException;
import eshop.mk.model.*;
import eshop.mk.model.modelRequests.CreateOrderFromPIRequest;
import eshop.mk.repository.JpaRepos.*;
import eshop.mk.service.OrderService;
import eshop.mk.service.ProductImagesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductItemRepository productItemRepository;
    private final JpaProductsRepository jpaProductsRepository;
    private final ProductImagesService productImagesService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(CartRepository cartRepository,
                            UsersRepository usersRepository,
                            CartItemRepository cartItemRepository,
                            ProductItemRepository productItemRepository,
                            JpaProductsRepository jpaProductsRepository,
                            ProductImagesService productImagesService,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository) {
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
        this.cartItemRepository = cartItemRepository;
        this.productItemRepository = productItemRepository;
        this.jpaProductsRepository = jpaProductsRepository;
        this.productImagesService = productImagesService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public String getCurrentLoggedInUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    @Transactional
    public void createOrderFromProductItem(CreateOrderFromPIRequest createOrderFromPIRequest) {

        User user = usersRepository.findUserByUsername(getCurrentLoggedInUser()).orElseThrow(UserNotFoundException::new);
        ProductItem pi = productItemRepository.findByProductItemId(createOrderFromPIRequest.getProductItemId()).orElseThrow(ProductItemNotFoundException::new);
        Product p = jpaProductsRepository.findByProductId(pi.getProduct());
        String imagePath = p.getProductImages().get(0).getImagePath();
        OrderItem orderItem = new OrderItem(UUID.randomUUID(), p.getProductName(), imagePath, pi, createOrderFromPIRequest.getOrderedQuantity(), null, null);
        Order order = new Order();
        order.setDateCreate(LocalDate.now());
        order.setTotalOrderPrice(pi.getPrice());
        order.setDiscountPrice(0.0);
        order.setSubTotalOrderPrice(pi.getPrice());
        order.setUser(user);
        Order o = orderRepository.save(order);
        orderItem.setOrder(o);
        orderItemRepository.save(orderItem);
        pi.setQuantityInStock(pi.getQuantityInStock() - createOrderFromPIRequest.getOrderedQuantity());
    }

    @Override
    @Transactional
    public void createOrderFromCart(UUID cartId) {

        Cart cart = cartRepository.findCartByCartId(cartId);
        List<OrderItem> orderItems = new LinkedList<>();

        if(cart == null){
            throw new CartNotFoundException();
        }
        User user = usersRepository.findUserByUsername(getCurrentLoggedInUser()).orElseThrow(UserNotFoundException::new);
        cart.getCartItems().forEach(it -> {
            ProductItem pi = productItemRepository.findByProductItemId(it.getProductItem().getProductItemId()).orElseThrow(ProductItemNotFoundException::new);
            Product p = jpaProductsRepository.findByProductId(pi.getProduct());
            String imagePath = p.getProductImages().get(0).getImagePath();
            OrderItem orderItem = new OrderItem(UUID.randomUUID(), p.getProductName(), imagePath, pi, it.getCartItemQuantity(), null, null);
            orderItems.add(orderItem);
            pi.setQuantityInStock(pi.getQuantityInStock() - it.getCartItemQuantity());
        });
        Order order = new Order();
        order.setDateCreate(LocalDate.now());
        order.setSubTotalOrderPrice(cart.getSubTotal());
        order.setTotalOrderPrice(cart.getTotal());
        order.setDiscountPrice(cart.getDiscount());
        order.setUser(user);
        Order o = orderRepository.save(order);
        orderItems.forEach(it ->{
            it.setOrder(o);
            orderItemRepository.save(it);
        });



    }


}
