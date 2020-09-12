package eshop.mk.model;

import eshop.mk.model.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductReview")
public class ProductReview extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Product productId;

    @Column(nullable = false, length = 1500)
    private String comment;

    @Column(nullable = false)
    private Integer grade;

    public ProductReview(User userId, Product productId, String comment, Integer grade){
        this.userId = userId;
        this.productId = productId;
        this.comment = comment;
        this.grade = grade;
    }
}
