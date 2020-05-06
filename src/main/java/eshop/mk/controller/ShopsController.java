package eshop.mk.controller;


import eshop.mk.model.Role;
import eshop.mk.model.Shop;
import eshop.mk.model.User;
import eshop.mk.model.modelDTOS.ShopCreationDTO;
import eshop.mk.repository.UsersRepository;
import eshop.mk.service.ShopsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/shops")
public class ShopsController {

    private final ShopsService shopsService;

    private final UsersRepository usersRepository;
    public ShopsController(ShopsService shopsService, UsersRepository usersRepository) {

        this.shopsService = shopsService;
        this.usersRepository = usersRepository;
    }


    @PostMapping(path = "/create")
    public String createShop(@RequestHeader UUID userId,
                             @RequestBody ShopCreationDTO shop
                             ){


        return shopsService.createShop(userId,shop);

    }


    @GetMapping(path = "/{shopId}")
    public Shop getShop(@PathVariable UUID shopId){
        return shopsService.getShop(shopId);
    }


    @GetMapping(path = "/allShops")
    public List<Shop> getAllShops(){
        return shopsService.getAllShops();
    };



}
