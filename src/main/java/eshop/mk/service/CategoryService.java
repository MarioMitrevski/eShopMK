package eshop.mk.service;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.projections.CategorySubcategories;

import java.util.List;


public interface CategoryService {

     List<Category> getAllCategories();

     Category findByCategoryId(Long categoryId);
     List<Category> getCategorySubcategories(Long categoryId);
}
