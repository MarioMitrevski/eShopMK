package eshop.mk.model;

import eshop.mk.model.auditing.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ProductReview")
public class ProductReview extends Auditable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID productId;

    @Column(nullable = false, length = 1500)
    private String comment;

    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 5)
    private Integer grade;
}
