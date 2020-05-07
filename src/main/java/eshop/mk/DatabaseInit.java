package eshop.mk;

import eshop.mk.model.Attribute;
import eshop.mk.model.Category;
import eshop.mk.model.Role;
import eshop.mk.repository.JpaRepos.AttributeRepository;
import eshop.mk.repository.JpaRepos.CategoriesRepository;
import eshop.mk.repository.JpaRepos.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "false")
public class DatabaseInit implements CommandLineRunner {
    private AttributeRepository attributeRepository;
    private CategoriesRepository categoriesRepository;
    private RolesRepository rolesRepository;
    public DatabaseInit(AttributeRepository attributeRepository, CategoriesRepository categoriesRepository,RolesRepository rolesRepository){
        this.attributeRepository = attributeRepository;
        this.categoriesRepository =  categoriesRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.attributeRepository.deleteAll();
        this.categoriesRepository.deleteAll();
        this.rolesRepository.deleteAll();

        List<Attribute> attributes = new ArrayList<>();


        attributes.add(new Attribute("SIZE_EU", "XS"));
        attributes.add(new Attribute("SIZE_EU", "S"));
        attributes.add(new Attribute("SIZE_EU", "M"));
        attributes.add(new Attribute("SIZE_EU", "L"));
        attributes.add(new Attribute("SIZE_EU", "XL"));
        attributes.add(new Attribute("SIZE_EU", "XXL"));
        attributes.add(new Attribute("SIZE_EU", "XXXL"));


        attributes.add(new Attribute("SIZE_SHOES", "20"));
        attributes.add(new Attribute("SIZE_SHOES", "22"));
        attributes.add(new Attribute("SIZE_SHOES", "24"));
        attributes.add(new Attribute("SIZE_SHOES", "26"));
        attributes.add(new Attribute("SIZE_SHOES", "28"));
        attributes.add(new Attribute("SIZE_SHOES", "30"));
        attributes.add(new Attribute("SIZE_SHOES", "34"));
        attributes.add(new Attribute("SIZE_SHOES", "36"));
        attributes.add(new Attribute("SIZE_SHOES", "38"));
        attributes.add(new Attribute("SIZE_SHOES", "40"));
        attributes.add(new Attribute("SIZE_SHOES", "41"));
        attributes.add(new Attribute("SIZE_SHOES", "42"));
        attributes.add(new Attribute("SIZE_SHOES", "43"));
        attributes.add(new Attribute("SIZE_SHOES", "44"));
        attributes.add(new Attribute("SIZE_SHOES", "45"));


        attributes.add(new Attribute("WAIST_EU", "XS"));
        attributes.add(new Attribute("WAIST_EU", "S"));
        attributes.add(new Attribute("WAIST_EU", "M"));
        attributes.add(new Attribute("WAIST_EU", "L"));
        attributes.add(new Attribute("WAIST_EU", "XL"));
        attributes.add(new Attribute("WAIST_EU", "XXL"));
        attributes.add(new Attribute("WAIST_EU", "XXXL"));

        attributes.add(new Attribute("COLOR", "BLUE"));
        attributes.add(new Attribute("COLOR", "RED"));
        attributes.add(new Attribute("COLOR", "WHITE"));
        attributes.add(new Attribute("COLOR", "BLACK"));
        attributes.add(new Attribute("COLOR", "GREEN"));
        attributes.add(new Attribute("COLOR", "YELLOW"));
        attributes.add(new Attribute("COLOR", "PINK"));
        attributes.add(new Attribute("COLOR", "PURPLE"));
        attributes.add(new Attribute("COLOR", "ORANGE"));
        attributes.add(new Attribute("COLOR", "GRAY"));
        attributes.add(new Attribute("COLOR", "BROWN"));

        attributes.add(new Attribute("MATERIAL", "GOLD"));
        attributes.add(new Attribute("MATERIAL", "BRONZE"));
        attributes.add(new Attribute("MATERIAL", "SILVER"));
        attributes.add(new Attribute("MATERIAL", "LEATHER"));
        attributes.add(new Attribute("MATERIAL", "COTTON"));
        attributes.add(new Attribute("MATERIAL", "WOOL"));
        attributes.add(new Attribute("MATERIAL", "SILK"));

        attributes.add(new Attribute("DIAMETER_INCHES", "1"));
        attributes.add(new Attribute("DIAMETER_CENTIMETERS", "1"));
        attributes.add(new Attribute("DIAMETER_METERS", "1"));
        attributes.add(new Attribute("DIAMETER_MILLIMETERS", "1"));

        attributes.add(new Attribute("LENGTH_CENTIMETERS", "1"));
        attributes.add(new Attribute("LENGTH_METERS", "1"));
        attributes.add(new Attribute("LENGTH_MILLIMETERS", "1"));
        attributes.add(new Attribute("LENGTH_INCHES", "1"));

        attributes.add(new Attribute("WEIGHT_GRAMS", "1"));
        attributes.add(new Attribute("WEIGHT_KILOS", "1"));


        this.attributeRepository.saveAll(attributes);


        /* Clothing&Shoes
               Men
                   T-shirts
                   Shirts
                   Jeans
                   Hoodies
                   Jackets
                   Shoes
               Women
                   T-shirts
                   Shirts
                   Jeans
                   Hoodies
                   Jackets
                   Shoes
               Kids
                   T-shirts
                   Shirts
                   Jeans
                   Hoodies
                   Jackets
                   Shoes

           Accessories
                Wallets
                Sunglasses
                Bags

           Jewellery
                Rings
                Earrings
                Necklace
                Bracelet

           Home
                Furniture
                Rugs
                Bedding
                    Blankets
                    Pillows
                Wall Decor
                Lightning

             */


        //Clothing&Shoes

        Category categoryClothes = new Category("Clothing&Shoes");
        this.categoriesRepository.save(categoryClothes);

        //MEN

        Category categoryMenClothes = new Category("Men", categoryClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenClothes);

        Category categoryMenTshirts = new Category("T-Shirts", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenTshirts);

        Category categoryMenShirts = new Category("Shirts", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenShirts);

        Category categoryMenJeans = new Category("Jeans", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenJeans);

        Category categoryMenHoodies = new Category("Hoodies", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenHoodies);

        Category categoryMenJackets = new Category("Jackets", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenJackets);

        Category categoryMenShoes = new Category("Shoes", categoryMenClothes.getCategoryId());
        this.categoriesRepository.save(categoryMenShoes);


        //WOMEN

        Category categoryWomenClothes = new Category("Women", categoryClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenClothes);

        Category categoryWomenTshirts = new Category("T-Shirts", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenTshirts);

        Category categoryWomenShirts = new Category("Shirts", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenShirts);

        Category categoryWomenJeans = new Category("Jeans", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenJeans);

        Category categoryWomenHoodies = new Category("Hoodies", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenHoodies);

        Category categoryWomenJackets = new Category("Jackets", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenJackets);

        Category categoryWomenShoes = new Category("Shoes", categoryWomenClothes.getCategoryId());
        this.categoriesRepository.save(categoryWomenShoes);


        // KIDS

        Category categoryKidsClothes = new Category("Kids", categoryClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsClothes);

        Category categoryKidsTshirts = new Category("T-Shirts", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsTshirts);

        Category categoryKidsShirts = new Category("Shirts", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsShirts);

        Category categoryKidsJeans = new Category("Jeans", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsJeans);

        Category categoryKidsHoodies = new Category("Hoodies", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsHoodies);

        Category categoryKidsJackets = new Category("Jackets", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsJackets);

        Category categoryKidsShoes = new Category("Shoes", categoryKidsClothes.getCategoryId());
        this.categoriesRepository.save(categoryKidsShoes);



        //Accessories

        Category categoryAccessories = new Category("Accessories");
        this.categoriesRepository.save(categoryAccessories);


        Category categoryWallets = new Category("Wallets", categoryAccessories.getCategoryId());
        this.categoriesRepository.save(categoryWallets);

        Category categorySunglasses = new Category("Sunglasses", categoryAccessories.getCategoryId());
        this.categoriesRepository.save(categorySunglasses);

        Category categoryBags = new Category("Bags", categoryAccessories.getCategoryId());
        this.categoriesRepository.save(categoryBags);

        //Jewellery

        Category categoryJewellery = new Category("Jewellery");
        this.categoriesRepository.save(categoryJewellery);


        Category categoryRings = new Category("Rings", categoryJewellery.getCategoryId());
        this.categoriesRepository.save(categoryRings);

        Category categoryEarrings = new Category("Earrings", categoryJewellery.getCategoryId());
        this.categoriesRepository.save(categoryEarrings);

        Category categoryNecklace = new Category("Necklace", categoryJewellery.getCategoryId());
        this.categoriesRepository.save(categoryNecklace);

        Category categoryBracelet = new Category("Bracelet", categoryJewellery.getCategoryId());
        this.categoriesRepository.save(categoryBracelet);


        //Home


        Category categoryHome = new Category("Home");
        this.categoriesRepository.save(categoryHome);


        Category categoryFurniture = new Category("Furniture", categoryHome.getCategoryId());
        this.categoriesRepository.save(categoryFurniture);

        Category categoryRugs = new Category("Rugs", categoryHome.getCategoryId());
        this.categoriesRepository.save(categoryRugs);

        Category categoryBedding = new Category("Bedding", categoryHome.getCategoryId());
        this.categoriesRepository.save(categoryBedding);

        Category categoryBlankets = new Category("Blankets", categoryBedding.getCategoryId());
        this.categoriesRepository.save(categoryBlankets);

        Category categoryPillows = new Category("Pillows", categoryBedding.getCategoryId());
        this.categoriesRepository.save(categoryPillows);

        Category categoryWallDecor = new Category("WallDecor", categoryHome.getCategoryId());
        this.categoriesRepository.save(categoryWallDecor);

        Category categoryLightning = new Category("Lightning", categoryHome.getCategoryId());
        this.categoriesRepository.save(categoryLightning);

        //ROLES

        this.rolesRepository.save(new Role("ROLE_USER"));
        this.rolesRepository.save(new Role("ROLE_SALES"));
        this.rolesRepository.save(new Role("ROLE_SHOPMANAGER"));


        System.out.println(" -- Database has been initialized");
    }
}