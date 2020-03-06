package eshop.mk.service.serviceImpl;

import eshop.mk.model.*;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.repository.ShopsRepository;
import eshop.mk.service.ProductsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final ShopsRepository shopsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository, ShopsRepository shopsRepository) {
        this.productsRepository = productsRepository;
        this.shopsRepository = shopsRepository;
    }

    @Override
    public void createProduct(UUID shopId,
                              String productName,
                              String type,
                              String productDescription,
                              Integer quantity) {

        Shop shop = shopsRepository.findByShopId(shopId);
        Product product = new Product();


        product.setShop(shop);
        product.setProductDescription(productDescription);
        product.setProductName(productName);
        product.setType(type);
        productsRepository.save(product);

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

}
