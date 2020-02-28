 package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")

class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    private User user;

    private Double totalPrice;
    private Date dateOfOrder;
    private StatusOrder statusOrder;


}
