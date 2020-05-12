package eshop.mk.repository.repositoryImpl;

import eshop.mk.model.Shop;
import eshop.mk.model.modelDTOS.ShopDTO;
import eshop.mk.repository.JpaRepos.JpaShopsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShopsRepositoryImpl {

    private final JpaShopsRepository jpaShopsRepository;

    public ShopsRepositoryImpl(JpaShopsRepository jpaShopsRepository) {
        this.jpaShopsRepository = jpaShopsRepository;
    }

    public Optional<Shop> getShop(UUID shopId){

        return jpaShopsRepository.findShopByShopId(shopId);
    }

    public Optional<ShopDTO> getShopForDetails(UUID shopId){

        return jpaShopsRepository.findByShopId(shopId);
    }

    public Shop saveShop(Shop shop){
        return jpaShopsRepository.save(shop);
    }


    public org.springframework.data.domain.Page<ShopDTO> findAllShops(int page,int size){
        return jpaShopsRepository.findAllBy(PageRequest.of(page,size));
    }

    public Optional<Shop> findByShopName(String shopName){
        return jpaShopsRepository.findByShopName(shopName);
    };

}
