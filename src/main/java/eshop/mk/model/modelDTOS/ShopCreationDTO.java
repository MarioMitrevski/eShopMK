package eshop.mk.model.modelDTOS;

import lombok.Data;

@Data
public class ShopCreationDTO {

    private String shopName;

    private String shopDescription;

    private String shopBankAccount;

    private String shopUTN;

    private Long shopCategory;

}
