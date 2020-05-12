package eshop.mk.model;

import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "ProductImages")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productImageId;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne
    private Product product;

}
