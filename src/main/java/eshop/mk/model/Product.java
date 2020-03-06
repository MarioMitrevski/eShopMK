package eshop.mk.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@Table(name = "Products")
public class Product {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID productId;

    @Column(nullable = false)
    private String productName;

    @ManyToOne
    private Shop shop;

    @ManyToMany
    private List<Category> categories;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String productDescription;



}


