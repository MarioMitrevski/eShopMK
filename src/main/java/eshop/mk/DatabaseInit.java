package eshop.mk;

import eshop.mk.model.*;
import eshop.mk.repository.JpaRepos.*;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DatabaseInit implements CommandLineRunner {
    private AttributeRepository attributeRepository;
    private CategoriesRepository categoriesRepository;
    private RolesRepository rolesRepository;
    private ShopsRepositoryImpl shopsRepository;
    private final JpaProductsRepository productsRepository;
    private final ProductImagesRepository productImagesRepository;

    public DatabaseInit(AttributeRepository attributeRepository,
                        CategoriesRepository categoriesRepository,
                        RolesRepository rolesRepository,
                        ShopsRepositoryImpl shopsRepository, JpaProductsRepository productsRepository, ProductImagesRepository productImagesRepository) {
        this.attributeRepository = attributeRepository;
        this.categoriesRepository = categoriesRepository;
        this.rolesRepository = rolesRepository;
        this.shopsRepository = shopsRepository;
        this.productsRepository = productsRepository;
        this.productImagesRepository = productImagesRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

       //this.attributeRepository.deleteAll();
      // this.categoriesRepository.deleteAll();
      //  this.rolesRepository.deleteAll();

        List<Attribute> attributes = new ArrayList<>();



        /*attributes.add(new Attribute("SIZE_EU", "XS"));
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




        *//* Clothing&Shoes 1 nul
               Men 2 1
                   T-shirts 3 2
                   Shirts 4 2
                   Jeans 5 2
                   Hoodies 6 2
                   Jackets7 2
                   Shoes 8 2
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

             *//*


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

        this.rolesRepository.save(new Role("USER"));
        this.rolesRepository.save(new Role("SALES"));
        this.rolesRepository.save(new Role("SHOPMANAGER"));

*/
        //SHOPS
    /*    productsRepository.deleteAll();

        shopsRepository.deleteAll();

        val shop = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear1", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear2", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear3", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear4", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear5", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear6", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear7", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear8", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear9", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear10", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear12", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear13", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear14", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear15", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear16", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear17", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear18", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear19", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear20", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear21", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear22", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear23", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear24", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear26", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear27", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear28", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear29", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear30", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear31", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear32", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear33", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear34", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear35", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear36", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear37", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear38", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear39", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear40", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));



        System.out.println(" -- Database has been initialized");
    }*/
        productsRepository.deleteAll();

        shopsRepository.deleteAll();

        val shop = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear1", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear2", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear3", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear4", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear5", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear6", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear7", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear8", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear9", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear10", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear12", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear13", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear14", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear15", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear16", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear17", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear18", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear19", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear20", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear21", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear22", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear23", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear24", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear26", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear27", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear28", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear29", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear30", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear31", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear32", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear33", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear34", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear35", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear36", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear37", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear38", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear39", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));
        shopsRepository.saveShop(new Shop(UUID.randomUUID(),"Pull&Bear40", "LOGO","description", Collections.emptyList(),"account","utn",categoriesRepository.findByCategoryId(1L).get()));

        val p1 = productsRepository.save(new Product(UUID.randomUUID(),"Tshirt",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123.3));
        val p2 =productsRepository.save(new Product(UUID.randomUUID(),"Jeans",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123333.0));
        val p3 =productsRepository.save(new Product(UUID.randomUUID(),"Tshirt2",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123.3));
        val p4 =productsRepository.save(new Product(UUID.randomUUID(),"Jeans2",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123.3));
        val p5 =productsRepository.save(new Product(UUID.randomUUID(),"Some long product name name name name!!!!!",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123.3));
        val p6 =productsRepository.save(new Product(UUID.randomUUID(),"Tshirt3",shop,"SKU123",false,categoriesRepository.findByCategoryId(1L).get(),"product description", 123.3));

        productImagesRepository.save(new ProductImage(1, p1.getShop().getShopName() + "_" + p1.getProductName() + "_" + 1, p1));
        productImagesRepository.save(new ProductImage(2, p2.getShop().getShopName() + "_" + p2.getProductName() + "_" + 1, p2));
        productImagesRepository.save(new ProductImage(3, p3.getShop().getShopName() + "_" + p3.getProductName() + "_" + 1, p3));
        productImagesRepository.save(new ProductImage(4, p4.getShop().getShopName() + "_" + p4.getProductName() + "_" + 1, p4));
        productImagesRepository.save(new ProductImage(5, p6.getShop().getShopName() + "_" + p6.getProductName() + "_" + 1, p6));
        productImagesRepository.save(new ProductImage(6, p5.getShop().getShopName() + "_" + p5.getProductName() + "_" + 1, p5));

    }
}