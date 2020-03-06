package eshop.mk.controller;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService){

        this.categoryService = categoryService;
    }

    @GetMapping(path="/all")
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }



}
