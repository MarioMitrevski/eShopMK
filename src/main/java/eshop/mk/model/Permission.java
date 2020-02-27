package eshop.mk.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
//@Table(name = "Permission")

public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    private String name;

    @ManyToMany
    private List<User> users;

}
