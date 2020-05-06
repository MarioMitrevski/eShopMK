package eshop.mk.controller;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
