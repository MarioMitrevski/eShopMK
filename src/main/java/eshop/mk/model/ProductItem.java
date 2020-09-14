package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eshop.mk.model.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
// @AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProductItem")
public class ProductItem extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productItemId;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Column(columnDefinition = "BINARY(16)")
    private UUID product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @JsonIgnore
    private boolean deleted;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Attribute> attributes = new HashSet<>();


    public ProductItem(UUID uuid, Integer quantityInStock, UUID productID, Double price, boolean deleted, Set<Attribute> attributes) {
        this.productItemId = uuid;
        this.quantityInStock = quantityInStock;
        this.product = productID;
        this.price = price;
        this.deleted = deleted;
        this.attributes = attributes;

    }
}
