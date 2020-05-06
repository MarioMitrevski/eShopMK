package eshop.mk.service.serviceImpl;


import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.model.Category;

import eshop.mk.repository.CategoriesRepository;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;

    public CategoryServiceImpl(CategoriesRepository categoriesRepository, ProductsRepository productsRepository){
        this.categoriesRepository =  categoriesRepository;
        this.productsRepository = productsRepository;
    }


    @Override
    public List<Category> getAllCategories() {
      try {
          return categoriesRepository.findAll();

      } catch (Exception ex){
          System.out.println(ex.getMessage());
          return null;
      }
    }




}
