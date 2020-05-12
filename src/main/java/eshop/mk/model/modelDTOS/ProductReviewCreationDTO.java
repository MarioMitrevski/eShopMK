package eshop.mk.model.modelDTOS;

import eshop.mk.model.Product;
import eshop.mk.model.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
public class ProductReviewCreationDTO {


    private UUID userId;

    private UUID productId;

    private String comment;

    @Min(value = 1)
    @Max(value = 5)
    private Integer grade;
}
