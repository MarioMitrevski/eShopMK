package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.net.URL;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID orderItemId;

    private String productName;

    private String imagePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private ProductItem productItem;

    @Column(nullable = false)
    private Integer orderItemQuantity;

    @Transient
    private URL imageUrl;

    @ManyToOne
    @JsonIgnore
    private Order order;



}
