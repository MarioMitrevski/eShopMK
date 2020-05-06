package eshop.mk.repository.repositoryImpl;

import eshop.mk.model.Page;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.repository.ProductsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl {

    private final ProductsRepository repository;


    public ProductRepositoryImpl(ProductsRepository repository) {
        this.repository = repository;
    }


    public Page<ProductForMainPageDTO> getProductsForMainPage(int page, int size, String sort,Long categoryId) {


        org.springframework.data.domain.Page<ProductForMainPageDTO> result = this.repository.findAllProductForMainBy(PageRequest.of(page,size, Sort.by(sort).descending()));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

}
