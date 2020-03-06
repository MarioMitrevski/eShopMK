package eshop.mk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micrometer.core.lang.NonNullApi;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "validUntilDate"},
        allowGetters = true)
public class Discount {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID discountId;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable  = false)
    @CreatedDate
    private LocalDate createdDate;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @CreatedDate
    private LocalDate validUntilDate;

    @Column(nullable = false)
    private Double discount;

}
