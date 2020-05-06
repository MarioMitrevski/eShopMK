package eshop.mk.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ProductItem")

public class ProductItem {

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
    private boolean deleted;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Attribute> attributes = new ArrayList<>();


}
