package eshop.mk.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import eshop.mk.model.auditing.Auditable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true) //radi Auditable
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Products")
public class Product extends Auditable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productId;

    @Column(nullable = false, length = 50)
    private String productName;

    @ManyToOne
    private Shop shop;


    private String productSKU;

    @ManyToOne
    private Category productCategory;

    @Column(nullable = false)
    private String productDescription;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<ProductItem> productItemList;


    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviews;

}

