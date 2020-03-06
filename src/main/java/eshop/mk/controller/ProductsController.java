package eshop.mk.controller;


import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }



    @PostMapping(path = "/post")
    public String createProduct(@RequestHeader UUID shopId,
                                @RequestParam String productName,
                                @RequestParam String type,
                                @RequestParam String productDescription,
                                @RequestParam Integer quantity){

        productsService.createProduct(shopId,productName,type,productDescription,quantity);
        return "Created";

    }

    @GetMapping(path="/all")
    public Page<Product> getAllProducts(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestHeader(name = "page-size", defaultValue = "20", required = false) int size)
    {
        return productsService.getAllProducts(page, size);
    }



    @GetMapping(path = "/{categoryName}")
    public Page<Product> getProductsByCategory(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                               @RequestHeader(name = "page-size", defaultValue = "20", required = false) int size,
                                               @PathVariable String categoryName){
        List<String> categoryList = new ArrayList<>();
        categoryList.add(categoryName);
        return productsService.getProductsByCategory(page, size, categoryList);

    }
    /*@GetMapping(path = "/{categoryName1}/{categoryName2}/")
    public Page<Product> getProductsByCategory(@PathVariable String categoryName1,@PathVariable String categoryName2){
        productsService.

    }    @GetMapping(path = "/{categoryName1}/{categoryName2}/{categoryName2}")
    public Page<Product> getProductsByCategory(@PathVariable String categoryName1,@PathVariable String categoryName2,@PathVariable String categoryName3){
        productsService.

    }    @GetMapping(path = "/{categoryName}")
    public Page<Product> getProductsByCategory(@PathVariable String categoryName){
        productsService.

    }*/


}