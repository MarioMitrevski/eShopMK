package eshop.mk;

import eshop.mk.model.projections.CategorySubcategories;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("mysql")
public class CategoryRepositoryTest {

    @Autowired
    public CategoriesRepository categoriesRepository;

    @Test
    public void findAllBySuperCategoryId(){
       // List<CategorySubcategories> lista = categoriesRepository.findAllBySuperCategoryId(1L);
        //lista.forEach(p->System.out.println(p.getCategoryId()));

    }
}
