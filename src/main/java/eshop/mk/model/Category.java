package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "Category")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;


    private Long superCategoryId;

    private String categoryName;
}
