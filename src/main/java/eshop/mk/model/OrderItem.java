package eshop.mk.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID orderItemId;

    @ManyToOne
    private Order order;
    @ManyToOne
    private ProductItem productItem;

    @Column(nullable = false)
    private Integer quantity;
}
