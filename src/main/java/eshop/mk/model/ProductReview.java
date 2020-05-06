package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ProductReview")
public class ProductReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private String comment;
    @Column(nullable = false)
    private Integer grade;
}
