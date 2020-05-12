package eshop.mk.model;

import eshop.mk.model.auditing.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
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
}
