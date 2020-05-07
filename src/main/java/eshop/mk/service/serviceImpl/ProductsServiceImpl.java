package eshop.mk.service.serviceImpl;

import com.google.cloud.storage.Blob;
import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import eshop.mk.repository.JpaRepos.ProductImagesRepository;
import eshop.mk.repository.JpaRepos.ShopsRepository;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
import eshop.mk.service.ProductImagesService;
import eshop.mk.service.ProductItemService;
import eshop.mk.service.ProductsService;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepositoryImpl productRepository;
    private final ShopsRepository shopsRepository;
    private final UsersRepository usersRepository;
    private final ProductItemService productItemService;
    private final CategoriesRepository categoriesRepository;
    private final ProductImagesService productImagesService;

    public ProductsServiceImpl(ProductsRepositoryImpl productRepository, ShopsRepository shopsRepository, UsersRepository usersRepository, ProductItemService productItemService, CategoriesRepository categoriesRepository, ProductImagesService productImagesService) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.productItemService = productItemService;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.productImagesService = productImagesService;

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

                    product.setDeleted(false);
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

        org.springframework.data.domain.Page<ProductForMainPageDTO> result =  productRepository.getProductsForMainPage(page,size,sort, categoryId);

        List<UUID> productIds = result.getContent().parallelStream().map(ProductForMainPageDTO::getProductId).collect(Collectors.toList());

        List<ProductImage> productImages = productImagesService.findProductImagesForProducts("1", productIds);

        result.getContent().parallelStream().forEach(forMainPageDTO -> {
            productImages.forEach(productImage -> {
                if(productImage.getProduct().equals(forMainPageDTO.getProductId())){
                    URL imageUrl = productImagesService.downloadProductFirstImage(productImage.getImagePath());
                    forMainPageDTO.setImageURL(imageUrl);
                }

            });
        });

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }


}
