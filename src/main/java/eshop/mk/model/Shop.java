package eshop.mk.model;

import eshop.mk.model.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Shop")
public class Shop extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID shopId;

    @Column(nullable = false)
    private String shopName;

    @Column
    private String shopLogoImage;

    @Column
    private String shopDescription;

    @OneToMany(targetEntity = User.class)
    private List<User> users;

    @Column(nullable = false)
    private String shopBankAccount;

    @Column(nullable = false)
    private String shopUTN;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category shopCategory;

    public Shop(UUID id,String name,String shopDescription){
        this.shopId = id;
        this.shopName = name;
        this.shopDescription = shopDescription;
    }
}
