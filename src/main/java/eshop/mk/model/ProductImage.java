package eshop.mk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity

@NoArgsConstructor
@AllArgsConstructor
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
