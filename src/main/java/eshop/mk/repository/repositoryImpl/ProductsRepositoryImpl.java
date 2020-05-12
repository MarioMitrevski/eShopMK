package eshop.mk.repository.repositoryImpl;

import eshop.mk.exceptions.ProductNotFoundException;
import eshop.mk.model.Category;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductDTO;
import eshop.mk.model.projections.ProductIdProjection;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import eshop.mk.repository.JpaRepos.JpaProductsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

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

    public List<ProductDTO> findProductByProductId(UUID productId){
        return this.repository.findProductByProductId(productId);
    }

    public ProductIdProjection findByProductId(UUID productID){
        return this.repository.findByProductIdAndDeleted(productID,false);
    }



    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPageByCategory(int page, int size, String sort, String order, List<Long> categorySubcategories) {


        if(order.equals("DESC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductForMainPageByCategory(false,categorySubcategories,PageRequest.of(page,size,Sort.by("p." + sort).descending()));


            return result;
        }
        else if(order.equals("ASC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductForMainPageByCategory(false,categorySubcategories, PageRequest.of(page,size,Sort.by("p." + sort).descending()));

            return result;
        }else{
            throw new ProductNotFoundException();
        }


    }

    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPage(int page, int size, String sort, String order) {


        if(order.equals("DESC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductForMainPage(false,PageRequest.of(page,size,Sort.by("p." + sort).descending()));


            return result;
        }
        else if(order.equals("ASC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductForMainPage(false, PageRequest.of(page,size,Sort.by("p." + sort).ascending()));

            return result;
        }else{
            throw new ProductNotFoundException();
        }


    }


    //Products for Shop methods

    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShop(int page, int size, String sort, String order, UUID shopId) {


        if(order.equals("DESC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductFromShop(false,shopId,PageRequest.of(page,size,Sort.by("p." + sort).descending()));


            return result;
        }
        else if(order.equals("ASC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductFromShop(false,shopId, PageRequest.of(page,size,Sort.by("p." + sort).ascending()));

            return result;
        }else{
            throw new ProductNotFoundException();
        }
    }
    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShopByCategory(int page, int size, String sort, String order, List<Long> categorySubcategories,UUID shopId) {


        if(order.equals("DESC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductFromShopByCategory(false,categorySubcategories,shopId,PageRequest.of(page,size,Sort.by("p." + sort).descending()));


            return result;
        }
        else if(order.equals("ASC")){
            System.out.println(sort);
            org.springframework.data.domain.Page<ProductsForMainPageProjection> result = this.repository.findAllProductFromShopByCategory(false,categorySubcategories,shopId, PageRequest.of(page,size,Sort.by("p." + sort).ascending()));

            return result;
        }else{
            throw new ProductNotFoundException();
        }


    }


}
