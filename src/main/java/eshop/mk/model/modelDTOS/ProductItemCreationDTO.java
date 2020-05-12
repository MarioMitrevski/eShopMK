package eshop.mk.model.modelDTOS;

import eshop.mk.model.Attribute;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ProductItemCreationDTO {


    private Double price;

    private Integer quantity;

    private List<Attribute> productItemAttributes;

}
