package eshop.mk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
//@Table(name = "Shop")

public class Shop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;


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



    @ManyToOne
    private Category category;
}
