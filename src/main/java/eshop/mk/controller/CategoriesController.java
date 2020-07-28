package eshop.mk.controller;

import eshop.mk.model.Category;
import eshop.mk.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
