package eshop.mk.service.serviceImpl;

import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.Shop;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.repository.ShopsRepository;
import eshop.mk.service.ProductsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final ShopsRepository shopsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository, ShopsRepository shopsRepository) {
        this.productsRepository = productsRepository;
        this.shopsRepository = shopsRepository;
    }

    @Override
    public void createProduct(Long shopId,
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
}
