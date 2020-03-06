package eshop.mk.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
//@Table(name = "ProductImages")

public class ProductImages {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productImageId;


    @Column(nullable = false)
    private String imagePath;
    private String description;

    @ManyToOne
    private Product product;

}
