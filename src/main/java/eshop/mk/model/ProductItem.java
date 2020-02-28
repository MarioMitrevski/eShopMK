package eshop.mk.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "ProductItem")

class ProductItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productItemId;


    @Column(nullable = false)
    private Integer quantityInStock;


    @ManyToOne
    private Product product;


    @Column(nullable = false)
    private Double price;
    private Double discount;

    @Column(nullable = false)
    private String productItemType;
}
