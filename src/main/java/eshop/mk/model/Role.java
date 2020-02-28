package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
//@Table(name = "Role")

class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String name;


    @ManyToMany
    private List<User> users;

}
