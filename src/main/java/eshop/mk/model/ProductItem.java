package eshop.mk.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
//@Table(name = "ProductItem")

class ProductItem {



    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productItemId;


    @Column(nullable = false)
    private Integer quantityInStock;


    @ManyToOne
    private Product product;


    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String productItemType;
}
