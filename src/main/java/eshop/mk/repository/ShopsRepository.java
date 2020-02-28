package eshop.mk.repository;

import eshop.mk.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopsRepository extends JpaRepository<Shop, Integer> {

    Shop findByShopId(Long id);
}
