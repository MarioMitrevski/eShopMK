package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ShopTableNotSavedException;
import eshop.mk.model.Category;
import eshop.mk.model.Role;
import eshop.mk.model.Shop;
import eshop.mk.model.User;
import eshop.mk.model.modelDTOS.ShopCreationDTO;
import eshop.mk.repository.*;
import eshop.mk.service.ShopsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopsService {


    private final ShopsRepository shopsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final RolesRepository rolesRepository;

    public ShopServiceImpl(ShopsRepository shopsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository, RolesRepository rolesRepository) {
        this.shopsRepository = shopsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.rolesRepository = rolesRepository;
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

                    shopsRepository.save(newShop);

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
        return shopsRepository.findByShopId(shopId);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopsRepository.findAll();
    }


}
