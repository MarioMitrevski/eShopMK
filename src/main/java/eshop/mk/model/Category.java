package eshop.mk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Category")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column
    private Long superCategoryId;

    @Column(nullable = false)
    private String categoryName;

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Category(String categoryName,Long superCategoryId){
        this.categoryName = categoryName;
        this.superCategoryId = superCategoryId;
    }
}
