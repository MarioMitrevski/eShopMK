package eshop.mk.repository.JpaRepos;

import eshop.mk.model.ProductReview;
import eshop.mk.model.modelDTOS.ProductReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface JpaProductReviewRepository extends JpaRepository<ProductReview,Long> {

    ProductReview findByUserIdAndProductId(UUID user, UUID product);


    @Query("select new eshop.mk.model.modelDTOS.ProductReviewDTO(u.firstName,u.lastName,rev.comment,rev.grade,rev.createdDate) from ProductReview rev join User u on u.userId=rev.userId where rev.productId=:productId")
    List<ProductReviewDTO> findAllByProductId(UUID productId);
}
