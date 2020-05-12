package eshop.mk.service.serviceImpl;


import eshop.mk.model.Category;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import eshop.mk.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoriesRepository categoriesRepository;

    public CategoryServiceImpl(CategoriesRepository categoriesRepository){
        this.categoriesRepository =  categoriesRepository;

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

    @Override
    public Category findByCategoryId(Long categoryId) {
        return categoriesRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Category> getCategorySubcategories(Long categoryId) {
        return categoriesRepository.getCategorySubcategories(categoryId);
    }
}
