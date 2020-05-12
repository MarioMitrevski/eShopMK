package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductNotFoundException;
import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.exceptions.ShopNotFoundException;
import eshop.mk.exceptions.UserNotFoundException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.*;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import eshop.mk.service.*;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepositoryImpl productRepository;
    private final ShopsRepositoryImpl shopsRepository;
    private final UsersRepository usersRepository;
    private final CategoryService categoryService;
    private final ProductItemService productItemService;
    private final ProductImagesService productImagesService;
    private final ProductReviewService productReviewService;

    public ProductsServiceImpl(ProductsRepositoryImpl productRepository, ShopsRepositoryImpl shopsRepository, UsersRepository usersRepository, CategoryService categoryService, ProductItemService productItemService, ProductImagesService productImagesService, ProductReviewService productReviewService) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.categoryService = categoryService;
        this.productItemService = productItemService;
        this.productRepository = productRepository;
        this.productImagesService = productImagesService;

        this.productReviewService = productReviewService;
    }



    @Override
    public Product createProduct(ProductCreationDTO productCreationDTO) {
        long startTime = System.currentTimeMillis();

        Shop shop = shopsRepository.getShop(productCreationDTO.getShopId()).orElseThrow(ShopNotFoundException::new);

            //TO DO TRY-CATCH and search for names of products in the same shop if contains throw exception
            User shopEmployee = usersRepository.findByUserIdAndShop(productCreationDTO.getUserId(),shop).orElseThrow(UserNotFoundException::new);
            //proveri dali vraboteniot ili menadzerot e od shop
                System.out.println("createProduct");
                //PROVERI DALI IMA PRODUKT SO ISTO IME OD PRODAVNICATA

                Product product = new Product();

                    product.setShop(shop);
                    product.setProductSKU(productCreationDTO.getProductSKU());
                    product.setProductDescription(productCreationDTO.getProductDescription());

                    product.setDeleted(false);
                    product.setProductName(productCreationDTO.getProductName());
                    Category productCategory = categoryService.findByCategoryId(productCreationDTO.getProductCategoryId());
                    if(productCategory != null){
                        product.setProductCategory(productCategory);

                    }else{
                        throw new ProductTableNotSavedException();
                    }


                productItemService.createProductItems(product, productCreationDTO.getProductItemCreationDTOS());


                long endTime = System.currentTimeMillis();
                System.out.println(startTime + " " + endTime);
                return product;




    }



    @Override
    public Page<ProductForMainPageDTO> getProducts(int page, int size, String sort,String order, Long categoryId) {
        List<ProductForMainPageDTO> productsDTO;
        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        if(categoryId != null){
            List<Category> categorySubcategories = categoryService.getCategorySubcategories(categoryId);
            List<Long> categories = categorySubcategories.parallelStream().map(Category::getCategoryId).collect(Collectors.toList());
            result = productRepository.findAllProductForMainPageByCategory(page,size,sort,order,categories);
            productsDTO = this.createProductForMainPageDTO(result.getContent());

        }else{
            result = productRepository.findAllProductForMainPage(page,size,sort,order);
            productsDTO = this.createProductForMainPageDTO(result.getContent());
        }
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }

    private List<ProductForMainPageDTO> createProductForMainPageDTO(List<ProductsForMainPageProjection> products){

        return products.stream().map(productsProjection -> {
            URL imageUrl = productImagesService.downloadProductImage(productsProjection.getImagePath());
            System.out.println(productsProjection.getProductName());
            return new ProductForMainPageDTO(productsProjection.getProductId(),productsProjection.getProductName(),productsProjection.getProductDescription(),productsProjection.getPrice(),imageUrl);
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsDTO getProduct(UUID productId) {


        List<ProductDTO> productDTO = productRepository.findProductByProductId(productId);

        if(productDTO.size() == 0){
            throw new ProductNotFoundException();
        }

        UUID productUUID = productDTO.get(0).getProductId();
        List<String> imagePaths = productDTO.parallelStream().map(ProductDTO::getImagePath).collect(Collectors.toList());
        List<URL> productImages = productImagesService.getProductImages(imagePaths);

        List<ProductItem> productItems = productItemService.getProductItems(productUUID);

        List<ProductReviewDTO> productReviews = productReviewService.findAllByProductId(productId);
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(productDTO.get(0).getProductId(),productDTO.get(0).getProductName(),productDTO.get(0).getProductDescription(),productDTO.get(0).getPrice(),productImages,productItems,productReviews);


        return productDetailsDTO;
    }



    //Get Products from Shop

    @Override
    public Page<ProductForMainPageDTO> getProductsFromShop(int page, int size, String sort,String order,UUID shopId) {

        org.springframework.data.domain.Page<ProductsForMainPageProjection> result = productRepository.findAllProductFromShop(page,size,sort,order,shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());


        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }


    @Override
    public Page<ProductForMainPageDTO> getProductsFromShopByCategory(int page, int size, String sort,String order, Long categoryId,UUID shopId) {


        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        List<Category> categorySubcategories = categoryService.getCategorySubcategories(categoryId);
        List<Long> categories = categorySubcategories.parallelStream().map(Category::getCategoryId).collect(Collectors.toList());
        result = productRepository.findAllProductFromShopByCategory(page,size,sort,order,categories,shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());


        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }



}
