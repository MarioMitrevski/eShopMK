package eshop.mk.model.modelDTOS;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ShopCreationDTO {

    private String shopName;

    private String shopDescription;

    private String shopBankAccount;

    private String shopUTN;

    private Long shopCategory;

}
