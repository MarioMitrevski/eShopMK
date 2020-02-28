package eshop.mk.model;


import io.micrometer.core.lang.NonNullApi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "ProductItemDetails")

public class ProductItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productItemDetailId;

    @ManyToOne
    private ProductItem productItem;
    @ManyToOne
    private Attribute attribute;
    private String value;



}
