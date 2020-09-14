package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID cartId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    private Double subTotal;

    private Double discount;

    private Double total;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private User user;
}
