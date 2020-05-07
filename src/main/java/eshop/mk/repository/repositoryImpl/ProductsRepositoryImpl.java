package eshop.mk.repository.repositoryImpl;

import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import eshop.mk.repository.JpaRepos.JpaProductsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductsRepositoryImpl {

    private final JpaProductsRepository repository;


    public ProductsRepositoryImpl(JpaProductsRepository repository) {
        this.repository = repository;
    }


    public Product save(Product product){
        return this.repository.save(product);
    }

    public Product findByProductId(UUID productID){
        return this.repository.findByProductId(productID);
    }


    public org.springframework.data.domain.Page<ProductForMainPageDTO> getProductsForMainPage(int page, int size, String sort, Long categoryId) {


        org.springframework.data.domain.Page<ProductForMainPageDTO> result = this.repository.findAllProductBy(PageRequest.of(page,size,Sort.by(sort).ascending()));

        return result;
    }
}
