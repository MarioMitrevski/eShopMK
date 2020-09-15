package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eshop.mk.model.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@Table(name = "Users")
public class User extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<ProductReview> productReviews = new ArrayList<>();

    @Column(columnDefinition = "BINARY(16)")
    private UUID shop;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Order> orderList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserAddress> userAddresses;

    public User() {

    }

    public User(UUID uuid, String firstName, String lastName, List<ProductReview> productReviews, UUID shopUUID, String username, String password, List<Role> roles) {
        this.userId = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.productReviews = productReviews;
        this.shop = shopUUID;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
