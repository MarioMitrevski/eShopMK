package eshop.mk.model;

import eshop.mk.model.auditing.Auditable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true) //radi Auditable
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Products")
public class Product extends Auditable{

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

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToOne
    private Category productCategory;

    @Column(nullable = false)
    private String productDescription;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductItem> productItemList;

    @OneToMany(mappedBy = "productId",cascade = CascadeType.ALL)
    private List<ProductReview> productReviews;
}


