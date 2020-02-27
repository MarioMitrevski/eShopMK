package eshop.mk.model;


import lombok.Data;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.persistence.*;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;


    @OneToOne
    private User user;


}
