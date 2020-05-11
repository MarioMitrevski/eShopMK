package eshop.mk.repository.JpaRepos;

import eshop.mk.model.ProductItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {

    @EntityGraph(attributePaths = "attributes")
    List<ProductItem> findAllByProductAndDeletedFalse(UUID productId);

}
