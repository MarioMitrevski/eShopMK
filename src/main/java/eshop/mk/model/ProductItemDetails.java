package eshop.mk.model;


import io.micrometer.core.lang.NonNullApi;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
//@Table(name = "ProductItemDetails")

public class ProductItemDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productItemDetailId;

    @ManyToOne
    private ProductItem productItem;
    @ManyToOne
    private Attribute attribute;

    @Column(nullable = false)
    private String value;



}
