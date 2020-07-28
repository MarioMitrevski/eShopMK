package eshop.mk.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "UserAddress")
public class UserAddress {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userAddressId;

    @Column(columnDefinition = "INT(4)", nullable = false)
    private Integer postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    private User user;
}
