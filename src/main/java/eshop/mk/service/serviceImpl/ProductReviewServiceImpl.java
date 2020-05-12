package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductReviewNotSavedException;
import eshop.mk.model.Product;
import eshop.mk.model.ProductReview;
import eshop.mk.model.modelDTOS.ProductReviewDTO;
import eshop.mk.model.projections.ProductIdProjection;
import eshop.mk.repository.repositoryImpl.ProductReviewRepositoryImpl;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
import eshop.mk.service.ProductReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductReviewRepositoryImpl productReviewRepository;
    private final ProductsRepositoryImpl productsRepository;
    public ProductReviewServiceImpl(ProductReviewRepositoryImpl productReviewRepository, ProductsRepositoryImpl productsRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productsRepository = productsRepository;
    }

    public ProductReview saveProductReview(ProductReview productReview){



        ProductIdProjection product = productsRepository.findByProductId(productReview.getProductId());
        if(product.getProductId() == null){
            throw new ProductReviewNotSavedException("Product does not exists");
        }
            ProductReview productReview1 = productReviewRepository.findProductReviewByUserAndProduct(productReview.getUserId(),productReview.getProductId());
            if(productReview1 == null) {
                try {
                    ProductReview productReview2 = productReviewRepository.saveProductReview(productReview);

                    return productReview2;
                }catch (Exception ex){
                    throw new ProductReviewNotSavedException(ex.getMessage());
                }

            }else{
                throw new ProductReviewNotSavedException("User already left review for this product");
            }


    }

    @Override
    public List<ProductReviewDTO> findAllByProductId(UUID productId) {
        return productReviewRepository.findAllByProductId(productId);
    }
}
