package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopsRepository extends JpaRepository<Shop, Integer> {

    Shop findByShopId(UUID shopId);
}
