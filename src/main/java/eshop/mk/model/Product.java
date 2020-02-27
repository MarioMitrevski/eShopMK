package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Products")
public
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @ManyToOne
    private Shop shop;

    @ManyToMany
    private List<Category> categories;


    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String productDescription;




}


