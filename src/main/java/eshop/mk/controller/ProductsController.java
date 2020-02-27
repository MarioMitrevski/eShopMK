package eshop.mk.controller;


import eshop.mk.service.ProductsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }










    /*@PostMapping(path = "/post")
    public String createProduct(@RequestHeader Long shopId,
                                @RequestParam String productName,
                                @RequestParam String type,
                                @RequestParam String productDescription,
                                @RequestParam Integer quantity){

        productsService.createProduct(shopId,productName,type,productDescription,quantity);
        return "Created";

    }*/


}