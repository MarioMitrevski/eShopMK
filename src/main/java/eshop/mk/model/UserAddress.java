package eshop.mk.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "UserAddress")

public class UserAddress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAddressId;

    private String city;
    private String street;
    private Integer number;

    @ManyToOne
    private User user;
}
