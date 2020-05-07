package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Attribute;
import eshop.mk.model.projections.AttributeNameUnitProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Long> {
}
