package eshop.mk.service;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.projections.CategorySubcategories;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

     List<Category> getAllCategories();

     Optional<Category> findByCategoryId(Long categoryId);
     List<Category> getCategorySubcategories(Long categoryId);
}
