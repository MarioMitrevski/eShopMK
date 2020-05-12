package eshop.mk.model.auditing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.NONE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {


    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;


    @LastModifiedDate
    @Column(name = "last_modified_date",updatable = false)
    private LocalDateTime lastModifiedDate;
}
