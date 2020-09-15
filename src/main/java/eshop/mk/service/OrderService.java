package eshop.mk.service;

import eshop.mk.model.modelRequests.CreateOrderFromPIRequest;

import java.util.UUID;


public interface OrderService {

    void createOrderFromProductItem(CreateOrderFromPIRequest createOrderFromPIRequest);

    void createOrderFromCart(UUID cartId);
}
