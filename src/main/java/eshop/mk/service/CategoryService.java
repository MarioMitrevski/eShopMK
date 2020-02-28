package eshop.mk.service;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;

import java.util.List;


public interface CategoryService {

     List<Category> getAllCategories();

     Page<Product> getAllProducts(int page, int size);

     Page<Product> getProductsByCategory(int page, int size, List<String> categoryList);
}
