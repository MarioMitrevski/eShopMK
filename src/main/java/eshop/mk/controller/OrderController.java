package eshop.mk.controller;


import eshop.mk.model.modelRequests.CreateOrderFromPIRequest;
import eshop.mk.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://192.168.100.16:3000")
@RequestMapping(path = "/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping("/createOrderFromProduct")
    private void createOrderFromProduct(@RequestBody CreateOrderFromPIRequest createOrderFromPIRequest){
        this.orderService.createOrderFromProductItem(createOrderFromPIRequest);
    }

    @PostMapping("/createOrderFromCart")
    private void createOrderFromCart(@RequestParam UUID cartId){
        this.orderService.createOrderFromCart(cartId);
    }


}