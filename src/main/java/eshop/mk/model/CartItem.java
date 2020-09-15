
package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.net.URL;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID cartItemId;

    private String productName;

    private String imagePath;

    @Transient
    private URL imageUrl;
    @ManyToOne
    @JsonIgnore
    private Cart cart;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private ProductItem productItem;

    @Column(nullable = false)
    private Integer cartItemQuantity;

}

