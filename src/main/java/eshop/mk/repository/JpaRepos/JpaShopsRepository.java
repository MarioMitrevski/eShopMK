package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Shop;
import eshop.mk.model.modelDTOS.ShopDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;

public interface JpaShopsRepository extends JpaRepository<Shop, UUID> {


    Optional<Shop> findShopByShopId(UUID shopId);

    @Query("select new eshop.mk.model.modelDTOS.ShopDTO(s.shopId,s.shopName,s.shopDescription,s.shopLogoImage,s.shopCategory.categoryId,s.createdDate) from Shop s where s.shopId=:shopId")
    Optional<ShopDTO> findByShopId(UUID shopId);

    @Query("select new eshop.mk.model.modelDTOS.ShopDTO(s.shopId,s.shopName,s.shopDescription,s.shopLogoImage,s.shopCategory.categoryId,s.createdDate) from Shop s")
    org.springframework.data.domain.Page<ShopDTO> findAllBy(Pageable pageable);



}
