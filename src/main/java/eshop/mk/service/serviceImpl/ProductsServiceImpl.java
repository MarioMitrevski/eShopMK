package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.repository.CategoriesRepository;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.repository.ShopsRepository;
import eshop.mk.repository.UsersRepository;
import eshop.mk.repository.repositoryImpl.ProductRepositoryImpl;
import eshop.mk.service.ProductItemService;
import eshop.mk.service.ProductsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepositoryImpl productRepository;
    private final ShopsRepository shopsRepository;
    private final UsersRepository usersRepository;
    private final ProductItemService productItemService;
    private final CategoriesRepository categoriesRepository;

    public ProductsServiceImpl(ProductRepositoryImpl productRepository, ShopsRepository shopsRepository, UsersRepository usersRepository, ProductItemService productItemService, CategoriesRepository categoriesRepository) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.productItemService = productItemService;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
    }



    @Override
    public String createProduct(ProductCreationDTO productCreationDTO) {
        long startTime = System.currentTimeMillis();
        Shop shop = shopsRepository.findByShopId(productCreationDTO.getShopId());

        if(shop != null){

            //TO DO TRY-CATCH and search for names of products in the same shop if contains throw exception
            User shopEmployee = usersRepository.findByUserIdAndShop(productCreationDTO.getUserId(),shop);
            //proveri dali vraboteniot ili menadzerot e od shop
            if(shopEmployee != null){
                System.out.println("createProduct");


                Product product = new Product();

                    product.setShop(shop);
                    product.setProductSKU(productCreationDTO.getProductSKU());
                    product.setProductDescription(productCreationDTO.getProductDescription());

                    product.setProductName(productCreationDTO.getProductName());
                    Category productCategory = categoriesRepository.findByCategoryId(productCreationDTO.getProductCategoryId());
                    if(productCategory != null){
                        product.setProductCategory(productCategory);

                    }else{
                        throw new ProductTableNotSavedException();
                    }


                productItemService.createProductItems(product, productCreationDTO.getProductItemCreationDTOS());


                long endTime = System.currentTimeMillis();
                System.out.println(startTime + " " + endTime);
                return "Uploadirano";

            }else{
                return "Korisnikot ne e del od prodavnicata";
            }
        }else{
            return "Nema takva prodavnica";
        }

    }



    @Override
    public Page<ProductForMainPageDTO> getProductsByCategory(int page, int size, String sort, Long categoryId) {

        return productRepository.getProductsForMainPage(page,size,sort, categoryId);
    }



/*
    @Override
    public Page<Product> getProductsByCategory(int page, int size, List<String> categoryList) {

    }
*/

}
