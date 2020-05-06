package eshop.mk.model.auditing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter(AccessLevel.NONE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableComplex<U> extends Auditable{




}
