package eshop.mk.controller;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.service.CategoryService;
import eshop.mk.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/categories")
public class CategoriesController {

    private final CategoryService categoryService;
    private final ProductsService productsService;

    public CategoriesController(CategoryService categoryService, ProductsService productsService){

        this.categoryService = categoryService;
        this.productsService = productsService;
    }

    @GetMapping(path="/all")
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping(path="/all")
    public Page<Product> getAllProducts(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestHeader(name = "page-size", defaultValue = "20", required = false) int size)
    {
        return categoryService.getAllProducts(page, size);
    }



    @GetMapping(path = "/{categoryName}")
    public Page<Product> getProductsByCategory(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                               @RequestHeader(name = "page-size", defaultValue = "20", required = false) int size,
                                               @PathVariable String categoryName){
        List<String> categoryList = new ArrayList<>();
        categoryList.add(categoryName);
        return categoryService.getProductsByCategory(page, size, categoryList);

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
