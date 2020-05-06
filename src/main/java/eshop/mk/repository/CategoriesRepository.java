package eshop.mk.repository;

import eshop.mk.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriesRepository  extends JpaRepository<Category,Integer> {


    Category findByCategoryId(Long categoryId);
}
