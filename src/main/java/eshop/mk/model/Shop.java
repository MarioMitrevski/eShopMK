package eshop.mk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

//@Table(name = "Shop")

public class Shop {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID shopId;


    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String shopBankAccount;

    @OneToOne
    private User user;


    @OneToMany(mappedBy = "product")
    private List<StoreReview> storeReviewList;

    @ManyToMany
    private List<Category> categories;
}
