package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ShopNotFoundException;
import eshop.mk.exceptions.ShopTableNotSavedException;
import eshop.mk.model.*;
import eshop.mk.model.modelDTOS.*;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import eshop.mk.repository.JpaRepos.RolesRepository;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import eshop.mk.service.ProductsService;
import eshop.mk.service.ShopsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopsService {


    private final ShopsRepositoryImpl shopsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final RolesRepository rolesRepository;
    private final ProductsService productsService;

    public ShopServiceImpl(ShopsRepositoryImpl shopsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository, RolesRepository rolesRepository, ProductsService productsService) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.rolesRepository = rolesRepository;
        this.productsService = productsService;
    }

    @Override
    public String createShop(UUID userId, ShopCreationDTO shopCreationDTO) throws ShopTableNotSavedException {


        User user = usersRepository.findByUserId(userId);
         List<Role> userRoles = user.getRoles();
            Shop newShop = new Shop();
            boolean shopOwner = userRoles.stream().anyMatch(r->r.getName().equals("ROLE_SHOPMANAGER"));
            if (shopOwner) {
                return "Корисникот веќе има креирано продавница";
            }else {
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
                try{

                    shopsRepository.saveShop(newShop);

                }catch (Exception e)
                {
                    System.out.println(e.getMessage());

                }
                Role shopOwnerRole = rolesRepository.findByName("ROLE_SHOPMANAGER");
                if(shopOwnerRole != null){
                    userRoles.add(shopOwnerRole);

                }else{

                    throw new ShopTableNotSavedException();
                }
                user.setShop(newShop);
                System.out.println("AMAN");
                try{

                    usersRepository.save(user);

                }catch (Exception e)
                {
                    System.out.println(e.getMessage());

                }
                System.out.println("KAKO E MOZNO");


                return "Креирана продавница" + newShop.getShopId().toString();
            }

    }


    @Override
    public Shop getShop(UUID shopId) {
        return shopsRepository.getShop(shopId).orElseThrow(ShopNotFoundException::new);
    }



    public ShopDetailsDTO getShopDetails(UUID shopId,int page,int size,String sort,String order){


        ShopDTO shop = shopsRepository.getShopForDetails(shopId).orElseThrow(ShopNotFoundException::new);
        //Zemi go shopot

        Page<ProductForMainPageDTO> productDTOSPage = productsService.getProductsFromShop(page,size,sort,order,shopId);

        return new ShopDetailsDTO(shop.getShopId(),shop.getShopName(),shop.getShopDescription(),shop.getCreatedDate(),shop.getShopCategory(),shop.getShopLogoImage(), productDTOSPage);
    }

    @Override
    public Page<ShopDTO> getAllShops(int page,int size) {

        org.springframework.data.domain.Page<ShopDTO> result = shopsRepository.findAllShops(page,size);


        //Dodadi url za slikite pa vrati
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                result.getContent());
    }


}
