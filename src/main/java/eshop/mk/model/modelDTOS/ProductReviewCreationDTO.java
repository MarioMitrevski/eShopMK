package eshop.mk.model.modelDTOS;

import lombok.Data;
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
