package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
//@Table(name = "ProductImages")

public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;
    private String imagePath;
    private String description;

    @ManyToOne
    private Product product;

}
