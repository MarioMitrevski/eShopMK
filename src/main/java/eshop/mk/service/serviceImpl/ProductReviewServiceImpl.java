package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductReviewNotSavedException;
import eshop.mk.model.Product;
import eshop.mk.model.ProductReview;
import eshop.mk.model.User;
import eshop.mk.model.modelDTOS.ProductReviewCreationDTO;
import eshop.mk.model.modelDTOS.ProductReviewDTO;
import eshop.mk.repository.JpaRepos.UsersRepository;
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
    private final UsersRepository usersRepository;
    public ProductReviewServiceImpl(ProductReviewRepositoryImpl productReviewRepository,
                                    ProductsRepositoryImpl productsRepository,
                                    UsersRepository usersRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productsRepository = productsRepository;
        this.usersRepository = usersRepository;
    }

    public void saveProductReview(ProductReviewCreationDTO productReviewCreationDTO){

        Product product = productsRepository.findByProductId(productReviewCreationDTO.getProductId());
        if(product == null){
            throw new ProductReviewNotSavedException();
        }
        User user = usersRepository.findByUserId(productReviewCreationDTO.getUserId()).orElseThrow( ProductReviewNotSavedException::new);
        if(user == null){
            throw new ProductReviewNotSavedException();

        }
        ProductReview productReview = productReviewRepository.findProductReviewByUserAndProduct(user,product);
        if(productReview != null) {
            throw new ProductReviewNotSavedException();
        }
        ProductReview created = new ProductReview();
        created.setComment(productReviewCreationDTO.getComment());
        created.setGrade(productReviewCreationDTO.getGrade());
        created.setUserId(user);
        created.setProductId(product);
        try {
            productReviewRepository.saveProductReview(created);
        }catch (Exception ex){
            throw new ProductReviewNotSavedException();
        }

    }

    @Override
    public List<ProductReviewDTO> findAllByProductId(UUID productId) {
        return productReviewRepository.findAllByProductId(productId);
    }
}
