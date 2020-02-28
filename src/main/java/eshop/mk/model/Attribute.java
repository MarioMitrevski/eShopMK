package eshop.mk.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "Attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeId;
    private final String attributeName;

}
