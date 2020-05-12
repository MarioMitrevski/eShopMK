package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.exceptions.ShopNotFoundException;
import eshop.mk.exceptions.ShopTableNotSavedException;
import eshop.mk.exceptions.UserNotFoundException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.*;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import eshop.mk.repository.JpaRepos.RolesRepository;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import eshop.mk.service.ProductImagesService;
import eshop.mk.service.ProductsService;
import eshop.mk.service.ShopsService;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopsService {

    private final ShopsRepositoryImpl shopsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final RolesRepository rolesRepository;
    private final ProductsService productsService;
    private final ProductImagesService imagesService;

    public ShopServiceImpl(ShopsRepositoryImpl shopsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository, RolesRepository rolesRepository, ProductsService productsService, ProductImagesService imagesService) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.rolesRepository = rolesRepository;
        this.productsService = productsService;
        this.imagesService = imagesService;
    }

    @Override
    public UUID createShop(UUID userId, ShopCreationDTO shopCreationDTO) throws ShopTableNotSavedException {


        User user = usersRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        List<Role> userRoles = user.getRoles();
        Shop newShop = new Shop();
        boolean shopOwner = userRoles.stream().anyMatch(r->r.getName().equals("ROLE_SHOPMANAGER"));
        if (shopOwner) {
            throw new ShopTableNotSavedException();
        }
        if(shopsRepository.findByShopName(shopCreationDTO.getShopName()).isPresent())
            throw new ShopTableNotSavedException();

        //if(shop.getShopUTN(); Proveri za danocen broj dali postoi,dali ima vekje prodavnica vekje
        newShop.setShopName(shopCreationDTO.getShopName());
        newShop.setShopUTN(shopCreationDTO.getShopUTN());
        newShop.setShopBankAccount(shopCreationDTO.getShopBankAccount());
        newShop.setShopDescription(shopCreationDTO.getShopDescription());
        if(shopCreationDTO.getShopCategory() == null){
            throw new ShopTableNotSavedException();
        }
        Category category = categoriesRepository.findByCategoryId(shopCreationDTO.getShopCategory());
        if(category != null){
            newShop.setShopCategory(category);
        }else{
            throw new ShopTableNotSavedException();
        }
        newShop.setShopLogoImage(newShop.getShopName());

        try{
            shopsRepository.saveShop(newShop);
        }catch (Exception e)
        {
            throw new ShopTableNotSavedException();
        }
        Role shopOwnerRole = rolesRepository.findByName("ROLE_SHOPMANAGER");
        if(shopOwnerRole != null){
            userRoles.add(shopOwnerRole);
        }else{
            throw new ShopTableNotSavedException();
        }
        user.setShop(newShop);

        try{
            usersRepository.save(user);
        }catch (Exception e)
        {
            throw new ShopTableNotSavedException();
        }
        return newShop.getShopId();
    }


    @Override
    public Optional<Shop> getShop(UUID shopId) {
        return shopsRepository.getShop(shopId);
    }



    public ShopDetailsDTO getShopDetails(UUID shopId,int page,int size,String sort,String order){


        ShopDTO shop = shopsRepository.getShopForDetails(shopId).orElseThrow(ShopNotFoundException::new);

        URL imageUrl = imagesService.downloadShopImage(shop.getShopLogoImage());

        Page<ProductForMainPageDTO> productDTOSPage = productsService.getProductsFromShop(page,size,sort,order,shopId);

        return new ShopDetailsDTO(shop.getShopId(),shop.getShopName(),shop.getShopDescription(),shop.getCreatedDate(),shop.getShopCategory(),imageUrl, productDTOSPage);
    }

    @Override
    public Page<ShopDTO> getAllShops(int page,int size) {

        org.springframework.data.domain.Page<ShopDTO> result = shopsRepository.findAllShops(page,size);

        result.getContent().forEach(shopDTO -> {
            URL imageUrl = imagesService.downloadShopImage(shopDTO.getShopLogoImage());
            shopDTO.setShopLogo(imageUrl);
        });
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                result.getContent());
    }


}
