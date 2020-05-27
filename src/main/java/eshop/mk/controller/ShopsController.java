package eshop.mk.controller;

import eshop.mk.model.Page;
import eshop.mk.model.modelDTOS.ShopCreationDTO;
import eshop.mk.model.modelDTOS.ShopDTO;
import eshop.mk.model.modelDTOS.ShopDetailsDTO;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.service.ShopsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api/shops")
public class ShopsController {

    private final ShopsService shopsService;

    public ShopsController(ShopsService shopsService) {

        this.shopsService = shopsService;
    }


    @PostMapping(path = "/create")
    @PreAuthorize("hasRole('ROLE_USER') && !hasAnyRole('ROLE_SHOPMANAGER', 'ROLE_SALES')")
    public UUID createShop(@RequestHeader UUID userId,
                             @RequestBody ShopCreationDTO shop){

        return shopsService.createShop(userId,shop);
    }


    @GetMapping(path = "/{shopId}")
    public ShopDetailsDTO getShop(@PathVariable UUID shopId,
                                  @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                  @RequestParam(name = "sortBy", defaultValue = "created_date",required = false) String sort,
                                  @RequestParam(name = "order", defaultValue = "DESC") String order){
        return shopsService.getShopDetails(shopId,page,size,sort,order);
    }


    @GetMapping(path = "/allShops")
    public Page<ShopDTO> getAllShops(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(name = "page-size", defaultValue = "10", required = false) int size){
        return shopsService.getAllShops(page, size);
    }



}
