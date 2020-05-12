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
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"modifiedDate", "dateOfOrder"},
        allowGetters = true)
class Order {

     @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
     @Column(columnDefinition = "BINARY(16)")
     private UUID orderId;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Double totalPrice;


    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable  = false)
    @CreatedDate
    private LocalDate dateOfOrder;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @CreatedDate
    private LocalDate modifiedDate;

    @Column(nullable = false)
    private StatusOrder statusOrder;

    @ManyToOne
    private UserAddress userAddress;



}
