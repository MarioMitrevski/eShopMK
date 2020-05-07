package eshop.mk.model.modelDTOS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode
public class ProductForMainPageDTO {

    private UUID productId;
    private Double price;
    private String productName;

    @JsonIgnore
    private LocalDateTime createdDate;

    @Transient
    private URL imageURL;

    public ProductForMainPageDTO(UUID productId, Double price, String productName,LocalDateTime createdDate){
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.createdDate = createdDate;
    }
}
