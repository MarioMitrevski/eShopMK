package eshop.mk.service.serviceImpl;


import eshop.mk.model.Category;
import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.repository.CategoriesRepository;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.service.CategoryService;
import org.springframework.data.domain.PageRequest;
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
    public Page<Product> getAllProducts(int page, int size) {

        org.springframework.data.domain.Page<Product> result = this.productsRepository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public Page<Product> getProductsByCategory(int page, int size, List<String> categoryList) {
        return null;
    }



/*
    @Override
    public Page<Product> getProductsByCategory(int page, int size, List<String> categoryList) {

    }
*/


    @Override
    public List<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }




}
