package eshop.mk.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"addedDate"},
        allowGetters = true)
public class CartItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID cartItemId;

    @ManyToOne
    private User user;
    @ManyToOne
    private ProductItem productItem;

    @Column(nullable = false)
    private Integer quantity;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable  = false)
    @CreatedDate
    private LocalDate addedDate;
}

