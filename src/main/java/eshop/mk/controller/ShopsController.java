package eshop.mk.controller;


import eshop.mk.model.Shop;
import eshop.mk.repository.ShopsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path="/api/shops")

public class ShopsController {

    private final ShopsRepository shopsRepository;

    public ShopsController(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }


    @PostMapping(path = "/create")
    public String createShop(@RequestHeader UUID shopId,
                             @RequestParam String image,
                             @RequestParam String name,
                             @RequestParam String shopBankAccount){

        Shop shop = new Shop();
        shop.setShopName(name);
        shop.setImagePath(image);
        shop.setShopBankAccount(shopBankAccount);
        shopsRepository.save(shop);
        return "Created Shop";
    }



}
