package eshop.mk;

import eshop.mk.model.*;
import eshop.mk.repository.JpaRepos.*;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UsersRepository usersRepository;
    private final JpaShopsRepository shopsJpaRepository;
    private final ProductItemRepository productItemRepository;
    private final JpaProductReviewRepository jpaProductReviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public DatabaseInit(AttributeRepository attributeRepository,
                        CategoriesRepository categoriesRepository,
                        RolesRepository rolesRepository,
                        ShopsRepositoryImpl shopsRepository,
                        JpaProductsRepository productsRepository,
                        ProductImagesRepository productImagesRepository,
                        UsersRepository usersRepository,
                        JpaShopsRepository shopsJpaRepository,
                        ProductItemRepository productItemRepository,
                        JpaProductReviewRepository jpaProductReviewRepository,
                        PasswordEncoder passwordEncoder,
                        CartItemRepository cartItemRepository,
                        CartRepository cartRepository) {
        this.attributeRepository = attributeRepository;
        this.categoriesRepository = categoriesRepository;
        this.rolesRepository = rolesRepository;
        this.shopsRepository = shopsRepository;
        this.productsRepository = productsRepository;
        this.productImagesRepository = productImagesRepository;
        this.usersRepository = usersRepository;
        this.shopsJpaRepository = shopsJpaRepository;
        this.productItemRepository = productItemRepository;
        this.jpaProductReviewRepository = jpaProductReviewRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;

    }

    @Override
    public void run(String... strings) throws Exception {


        this.cartItemRepository.deleteAll();
        this.cartRepository.deleteAll();
        this.attributeRepository.deleteAll();
        this.categoriesRepository.deleteAll();
        this.rolesRepository.deleteAll();
        this.usersRepository.deleteAll();
        productImagesRepository.deleteAll();
        productsRepository.deleteAll();
        productItemRepository.deleteAll();
        shopsRepository.deleteAll();



        List<Attribute> attributes = new ArrayList<>();
        if (this.attributeRepository.count() == 0) {
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
            attributes.add(new Attribute("MATERIAL", "POLYPROPYLENE"));
            attributes.add(new Attribute("MATERIAL", "PVC"));

            attributes.add(new Attribute("DIAMETER_INCHES", "1"));
            attributes.add(new Attribute("DIAMETER_CENTIMETERS", "1"));
            attributes.add(new Attribute("DIAMETER_METERS", "1"));
            attributes.add(new Attribute("DIAMETER_MILLIMETERS", "1"));

            attributes.add(new Attribute("LENGTH_CENTIMETERS", "1"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "20"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "40"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "60"));

            attributes.add(new Attribute("LENGTH_METERS", "1"));
            attributes.add(new Attribute("LENGTH_MILLIMETERS", "1"));
            attributes.add(new Attribute("LENGTH_INCHES", "1"));

            attributes.add(new Attribute("WEIGHT_GRAMS", "1"));
            attributes.add(new Attribute("WEIGHT_KILOS", "1"));

            this.attributeRepository.saveAll(attributes);

        }

        if (this.rolesRepository.count() == 0) {
            this.rolesRepository.save(new Role("USER"));
            this.rolesRepository.save(new Role("SALES"));
            this.rolesRepository.save(new Role("SHOPMANAGER"));
        }

        if (this.categoriesRepository.count() == 0) {

            Category categoryClothes = new Category("Clothing&Shoes");
            this.categoriesRepository.save(categoryClothes);

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

            Category categoryAccessories = new Category("Accessories");
            this.categoriesRepository.save(categoryAccessories);

            Category categoryWallets = new Category("Wallets", categoryAccessories.getCategoryId());
            this.categoriesRepository.save(categoryWallets);

            Category categorySunglasses = new Category("Sunglasses", categoryAccessories.getCategoryId());
            this.categoriesRepository.save(categorySunglasses);

            Category categoryBags = new Category("Bags", categoryAccessories.getCategoryId());
            this.categoriesRepository.save(categoryBags);

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

        }

        if (usersRepository.count() == 0) {
            List<Role> roles_user_shop_owners = new LinkedList<>();
            roles_user_shop_owners.add(rolesRepository.findByName("SHOPMANAGER"));
            List<Role> roles_user_users = new LinkedList<>();
            roles_user_users.add(rolesRepository.findByName("USER"));
            User user1 = new User(UUID.randomUUID(), "Pavel", "Jankoski", null, null, "pavel.jankoski@gmail.com", "pavel123", roles_user_shop_owners);
            usersRepository.save(user1);
            User user2 = new User(UUID.randomUUID(), "Mario", "Mitrevski", null, null, "matrio.mitrevski@gmail.com", "mario123", roles_user_shop_owners);
            usersRepository.save(user2);
            User user3 = new User(UUID.randomUUID(), "Andrej", "Anchevski", null, null, "andrej.ancevski@gmail.com", "ance123", roles_user_shop_owners);
            usersRepository.save(user3);
            User user4 = new User(UUID.randomUUID(), "Oliver", "Cvetkovski", null, null, "oli.cvetko@gmail.com", "oli123", roles_user_shop_owners);
            usersRepository.save(user4);
            User user5 = new User(UUID.randomUUID(), "GoceGP", "Cvetkovski", null, null, "goce.gp@gmail.com", "gocegp123", roles_user_shop_owners);
            usersRepository.save(user5);
            User user6 = new User(UUID.randomUUID(), "Aldin", "Cosovic", null, null, "ado.cosovic@gmail.com", "ado123456", roles_user_shop_owners);
            usersRepository.save(user6);
            User user7 = new User(UUID.randomUUID(), "Teo", "Dimitriovski", null, null, "teo.fudbal@gmail.com", "teo456123", roles_user_users);
            usersRepository.save(user7);
            User user8 = new User(UUID.randomUUID(), "Andrej", "Vlaski", null, null, "andrej.vlaski@gmail.com", "vlaskizver", roles_user_users);
            usersRepository.save(user8);
            User user9 = new User(UUID.randomUUID(), "Borche", "Antovski", null, null, "borce.amerika@gmail.com", "borce123456", roles_user_users);
            usersRepository.save(user9);
            User user10 = new User(UUID.randomUUID(), "Mario", "Kotevski", null, null, "mario.lilard@gmail.com", "mario123", roles_user_users);
            user10.setPassword(passwordEncoder.encode(user10.getPassword()));
            usersRepository.save(user10);
            User user11 = new User(UUID.randomUUID(), "Daniela", "Blazevska", null, null, "dani.semos@gmail.com", "danibiljana", roles_user_shop_owners);
            usersRepository.save(user11);
            User user12 = new User(UUID.randomUUID(), "Sara", "Gajdovska", null, null, "sari.maci@gmail.com", "sarisarisari", roles_user_shop_owners);
            usersRepository.save(user12);
            User user13 = new User(UUID.randomUUID(), "Mila", "Rakovik", null, null, "mila.linkedin@gmail.com", "milavarga", roles_user_shop_owners);
            usersRepository.save(user13);
            User user14 = new User(UUID.randomUUID(), "Ema", "Manasievska", null, null, "ema.pecs@gmail.com", "ema123123", roles_user_shop_owners);
            usersRepository.save(user14);
            User user15 = new User(UUID.randomUUID(), "Kalina", "Petkova", null, null, "kalina.zver@gmail.com", "srekjenrodenden", roles_user_shop_owners);
            usersRepository.save(user15);
            User user16 = new User(UUID.randomUUID(), "Milena", "Janevska", null, null, "milena.janevska@gmail.com", "milena123", roles_user_users);
            usersRepository.save(user16);
            User user17 = new User(UUID.randomUUID(), "Davor", "Binov", null, null, "davor.anhoch@gmail.com", "davor456", roles_user_users);
            usersRepository.save(user17);
            User user18 = new User(UUID.randomUUID(), "Mila", "Penikin", null, null, "mila.redbull@gmail.com", "mila123", roles_user_users);
            usersRepository.save(user18);
            User user19 = new User(UUID.randomUUID(), "Varga", "Mitrevski", null, null, "varga.car@gmail.com", "varga123123", roles_user_users);
            usersRepository.save(user19);
            User user20 = new User(UUID.randomUUID(), "Stefi", "Kostic", null, null, "stefi.papagal@gmail.com", "stefi123", roles_user_users);
            usersRepository.save(user20);
            User user21 = new User(UUID.randomUUID(), "Decko", "NaStefi", null, null, "deckoto.stefi@gmail.com", "toj123", roles_user_users);
            usersRepository.save(user21);
            User user22 = new User(UUID.randomUUID(), "Goran", "Mihailovski", null, null, "goran.mihailovski@gmail.com", "goran123", roles_user_users);
            usersRepository.save(user22);
            User user23 = new User(UUID.randomUUID(), "Aneta", "Ancevska", null, null, "aneta.ancevska@gmail.com", "aneta123", roles_user_users);
            usersRepository.save(user23);
            User user24 = new User(UUID.randomUUID(), "Mario", "Nikolovski", null, null, "mario.nikoloski@gmail.com", "mario123", roles_user_users);
            usersRepository.save(user24);
            User user25 = new User(UUID.randomUUID(), "Alek", "Kolarik", null, null, "alek.kolarik@gmail.com", "alek123", roles_user_users);
            usersRepository.save(user25);
            User user26 = new User(UUID.randomUUID(), "Ace", "Simonov", null, null, "ace.simonov@gmail.com", "aco123123123", roles_user_users);
            usersRepository.save(user26);
            User user27 = new User(UUID.randomUUID(), "Filip", "Simonov", null, null, "filip.simonov@gmail.com", "fico123123123", roles_user_users);
            usersRepository.save(user27);
            User user28 = new User(UUID.randomUUID(), "Goran", "Krstevski", null, null, "goran.krstevski@gmail.com", "kico444", roles_user_users);
            usersRepository.save(user28);
            User user29 = new User(UUID.randomUUID(), "Leon", "Krstevski", null, null, "leon.krstevski@gmail.com", "leon4455", roles_user_users);
            user29.setPassword(passwordEncoder.encode(user29.getPassword()));
            usersRepository.save(user29);
            User user30 = new User(UUID.randomUUID(), "Boge", "Boge", null, null, "boge.boge@gmail.com", "bogedekan", roles_user_users);
            usersRepository.save(user30);
            User user31 = new User(UUID.randomUUID(), "Tea", "Tea", null, null, "tea.tea@gmail.com", "teadekan", roles_user_users);
            usersRepository.save(user31);
            User user32 = new User(UUID.randomUUID(), "Ljupce", "Ljupce", null, null, "ljupce.ljupce@gmail.com", "ljupcedekan", roles_user_users);
            usersRepository.save(user32);


        }


        List<User> avangardaUsers = new LinkedList<>();
        avangardaUsers.add(this.usersRepository.findUserByUsername("oli.cvetko@gmail.com").get());
        Category categoryAvangarda = categoriesRepository.findByCategoryId(1L).get();
        Shop avangarda = shopsRepository.saveShop(new Shop(UUID.randomUUID(), "Avangarda", "Avangarda",
                "Go with us and be creative and abstract all the way", avangardaUsers, "123469123123", "2342352342", categoryAvangarda));

        List<User> homeEnjoyUsers = new LinkedList<>();
        homeEnjoyUsers.add(this.usersRepository.findUserByUsername("andrej.ancevski@gmail.com").get());
        Category homeEnjoyCategory = categoriesRepository.findByCategoryId(32L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "HomeEnjoyShop", "HomeEnjoyShop",
                "Enjoy the comfort of your own home", homeEnjoyUsers, "123456123444", "1093939499", homeEnjoyCategory));

        List<User> funnyRugsUsers = new LinkedList<>();
        funnyRugsUsers.add(this.usersRepository.findUserByUsername("matrio.mitrevski@gmail.com").get());
        Category funnyRugsCategory = categoriesRepository.findByCategoryId(34L).get();
        Shop funnyRugsShop = shopsRepository.saveShop(new Shop(UUID.randomUUID(), "FunnyRugsShop", "FunnyRugsShop",
                "Smile while you look down", funnyRugsUsers, "67832323111", "39454039530943", funnyRugsCategory));

        List<User> groomRingsUsers = new LinkedList<>();
        groomRingsUsers.add(this.usersRepository.findUserByUsername("pavel.jankoski@gmail.com").get());
        Category groomRingsCategory = categoriesRepository.findByCategoryId(28L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "GroomRings", "GroomRings",
                "Rings not only for brides, be classy all the time", groomRingsUsers, "8389391921", "9490904390439043", groomRingsCategory));

        List<User> emojiClothingUsers = new LinkedList<>();
        emojiClothingUsers.add(this.usersRepository.findUserByUsername("goce.gp@gmail.com").get());
        Category emojiClothingCategory = categoriesRepository.findByCategoryId(1L).get();
        Shop emojiClothingShop = shopsRepository.saveShop(new Shop(UUID.randomUUID(), "EmojiClothing", "EmojiClothing",
                "Cool, yellow and online everywhere u go", emojiClothingUsers, "12100000", "88940375", emojiClothingCategory));

        List<User> cozyBlanketsUsers = new LinkedList<>();
        emojiClothingUsers.add(this.usersRepository.findUserByUsername("dani.semos@gmail.com").get());
        Category cozyBlanketCategory = categoriesRepository.findByCategoryId(36L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "CozyBlankets", "CozyBlankets",
                "You will never feel the winter with our wonderful products", cozyBlanketsUsers, "12309u8", "90767876", cozyBlanketCategory));

        List<User> marvelWallDecorUsers = new LinkedList<>();
        marvelWallDecorUsers.add(this.usersRepository.findUserByUsername("sari.maci@gmail.com").get());
        Category marvelWallDecorCategory = categoriesRepository.findByCategoryId(38L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "MarvelWallDecor", "MarvelWallDecor",
                "Be with your superheroes wherever you look", marvelWallDecorUsers, "774949393", "98232920390", marvelWallDecorCategory));

        List<User> engravedLeatherWalletUsers = new LinkedList<>();
        engravedLeatherWalletUsers.add(this.usersRepository.findUserByUsername("mila.linkedin@gmail.com").get());
        Category engravedLeatherWalletCategory = categoriesRepository.findByCategoryId(24L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "EngravedLeatherWallet", "EngravedLeatherWallet",
                "Classy wallets with your initals", engravedLeatherWalletUsers, "09809098908089", "123213123", engravedLeatherWalletCategory));

        List<User> embroideredJeansUsers = new LinkedList<>();
        embroideredJeansUsers.add(this.usersRepository.findUserByUsername("ema.pecs@gmail.com").get());
        Category embroideredJeansCategory = categoriesRepository.findByCategoryId(12L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "EmbroideredJeans", "EmbroideredJeans",
                "Your jeans even cooler than normal", embroideredJeansUsers, "123090000", "087665787", embroideredJeansCategory));

        List<User> flowerPillowsUsers = new LinkedList<>();
        flowerPillowsUsers.add(this.usersRepository.findUserByUsername("kalina.zver@gmail.com").get());
        Category flowerPillowsCategory = categoriesRepository.findByCategoryId(37L).get();
        shopsRepository.saveShop(new Shop(UUID.randomUUID(), "FlowerPillows", "FlowerPillows",
                "Your head will be like on a flower garden", flowerPillowsUsers, "0809909090344", "09093099039080", flowerPillowsCategory));

        Shop shop1 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop1","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop2 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop2","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop3 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop3","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop4 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop4","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop5 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop5","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop6 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop6","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop7 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop7","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop8 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop8","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop9 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop9","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop10 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop10","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop11 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop11","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop12 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop12","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop13 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop13","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop14 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop14","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop15 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop15","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop16 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop16","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop17 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop17","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop18 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop18","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop19 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop19","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));
        Shop shop20 = shopsRepository.saveShop(new Shop(UUID.randomUUID(),"TestShop20","TestShop","This is a description for this shop",null,"11111111111","11111111111",
                categoriesRepository.findByCategoryId(1L).get()));





        Product avangardaProduct1 = productsRepository.save(new Product(UUID.randomUUID(), "OCULUSSOCKS", avangarda, "123U54123", false, categoriesRepository.findByCategoryId(1L).get(),
                "Checkout the red socks of our OCULUS collection", null, 3.00, null, null));
        Set<Attribute> attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(17L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(18L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(19L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(20L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(21L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        attributesSocks = new HashSet<>();
        attributesSocks.add(attributeRepository.findById(22L).get());
        attributesSocks.add(attributeRepository.findById(31L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct1.getProductId(), 3.00, false, attributesSocks));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("ado.cosovic@gmail.com").get(), avangardaProduct1, "Nice and cool. Great way to show some crazy style", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("borce.amerika@gmail.com").get(), avangardaProduct1, "I can't say how comfy they are", 5));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct1.getProductName() + "_" + 1, avangardaProduct1));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct1.getProductName() + "_" + 2, avangardaProduct1));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct1.getProductName() + "_" + 3, avangardaProduct1));


        Product avangardaProduct2 = productsRepository.save(new Product(UUID.randomUUID(), "TUAMHOODIE", avangarda, "45654UIR", false, categoriesRepository.findByCategoryId(1L).get(),
                "POBJA - ARANEA TUAM - Abstract way of life. Be cool in Black", null, 40.00, null, null));
        Set<Attribute> attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(1L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct2.getProductId(), 40.00, false, attributesHoodie));
        attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(2L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct2.getProductId(), 40.00, false, attributesHoodie));
        attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(3L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct2.getProductId(), 40.00, false, attributesHoodie));
        attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(4L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct2.getProductId(), 40.00, false, attributesHoodie));
        attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(5L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct2.getProductId(), 40.00, false, attributesHoodie));
        attributesHoodie = new HashSet<>();
        attributesHoodie.add(attributeRepository.findById(6L).get());
        attributesHoodie.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct1.getProductId(), 40.00, false, attributesHoodie));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("mario.lilard@gmail.com").get(), avangardaProduct2, "A little bit bigger for shown sizes", 3));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("andrej.vlaski@gmail.com").get(), avangardaProduct2, "Great. I wear it everyday", 5));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct2.getProductName() + "_" + 1, avangardaProduct2));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct2.getProductName() + "_" + 2, avangardaProduct2));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct2.getProductName() + "_" + 3, avangardaProduct2));

        Product avangardaProduct3 = productsRepository.save(new Product(UUID.randomUUID(), "ENIGMAHOODIE", avangarda, "123UKR123", false, categoriesRepository.findByCategoryId(1L).get(),
                "Enigma - Tenner Hoodie. White and clean. Your drip perfect", null, 35.00, null, null));
        Set<Attribute> attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(1L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 3, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(2L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(3L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(4L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(5L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        attributesHoodie2 = new HashSet<>();
        attributesHoodie2.add(attributeRepository.findById(6L).get());
        attributesHoodie2.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, avangardaProduct3.getProductId(), 35.00, false, attributesHoodie2));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("davor.anhoch@gmail.com").get(), avangardaProduct3, "The material is itchy", 2));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("milena.janevska@gmail.com").get(), avangardaProduct3, "What a comfy clothing and goes with everything", 5));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct3.getProductName() + "_" + 1, avangardaProduct3));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct3.getProductName() + "_" + 2, avangardaProduct3));

        Product avangardaProduct4 = productsRepository.save(new Product(UUID.randomUUID(), "INFINITUSCEREBRI", avangarda, "167SKU123", false, categoriesRepository.findByCategoryId(1L).get(),
                "Unique color and one of our best - sellers", null, 22.00, null, null));
        Set<Attribute> attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(1L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(2L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(3L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(4L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(5L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        attributesShirt1 = new HashSet<>();
        attributesShirt1.add(attributeRepository.findById(6L).get());
        attributesShirt1.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, avangardaProduct4.getProductId(), 22.00, false, attributesShirt1));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("mila.redbull@gmail.com").get(), avangardaProduct4, "Definitely one of your best products", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("varga.car@gmail.com").get(), avangardaProduct4, "I will buy more the second is out of stock. Highest grade from me", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("teo.fudbal@gmail.com").get(), avangardaProduct4, "The material is perfect and design is impeccable", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("ado.cosovic@gmail.com").get(), avangardaProduct4, "Maybe different color", 4));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct4.getProductName() + "_" + 1, avangardaProduct4));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct4.getProductName() + "_" + 2, avangardaProduct4));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct4.getProductName() + "_" + 3, avangardaProduct4));


        Product avangardaProduct5 = productsRepository.save(new Product(UUID.randomUUID(), "SOMNIUMSHIRT", avangarda, "12UHE56", false, categoriesRepository.findByCategoryId(1L).get(),
                "SURREAL SOMNIUM - step into the surreality", null, 22.00, null, null));
        Set<Attribute> attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(1L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(2L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(3L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(4L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(5L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        attributesShirt2 = new HashSet<>();
        attributesShirt2.add(attributeRepository.findById(6L).get());
        attributesShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, avangardaProduct5.getProductId(), 22.00, false, attributesShirt2));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("stefi.papagal@gmail.com").get(), avangardaProduct5, "The sticker got of after 3 washes", 1));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("deckoto.stefi@gmail.com").get(), avangardaProduct5, "Cool, maybe little simple, material okay", 4));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct5.getProductName() + "_" + 1, avangardaProduct5));
        productImagesRepository.save(new ProductImage(avangarda.getShopName() + "_" + avangardaProduct5.getProductName() + "_" + 2, avangardaProduct5));

        Product funnyRugsProduct1 = productsRepository.save(new Product(UUID.randomUUID(), "OpticalIllusionRug", funnyRugsShop, "561ytryR", false, categoriesRepository.findByCategoryId(34L).get(),
                "Checkout this 3D magic. Feel like having a deep hole down your living room", null, 50.00, null, null));
        Set<Attribute> attributesOpticalRug = new HashSet<>();
        attributesOpticalRug.add(attributeRepository.findById(48L).get());
        attributesOpticalRug.add(attributeRepository.findById(54L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, funnyRugsProduct1.getProductId(), 30.00, false, attributesOpticalRug));
        attributesOpticalRug = new HashSet<>();
        attributesOpticalRug.add(attributeRepository.findById(48L).get());
        attributesOpticalRug.add(attributeRepository.findById(55L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, funnyRugsProduct1.getProductId(), 50.00, false, attributesOpticalRug));
        attributesOpticalRug = new HashSet<>();
        attributesOpticalRug.add(attributeRepository.findById(48L).get());
        attributesOpticalRug.add(attributeRepository.findById(56L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 3, funnyRugsProduct1.getProductId(), 70.00, false, attributesOpticalRug));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("goran.mihailovski@gmail.com").get(), funnyRugsProduct1, "Amazed by this product. Recommending it definitely", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("aneta.ancevska@gmail.com").get(), funnyRugsProduct1, "Cheap material not recommendable", 1));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("mario.lilard@gmail.com").get(), funnyRugsProduct1, "Made my guest laugh. Cool addition to your homes", 5));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct1.getProductName() + "_" + 1, funnyRugsProduct1));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct1.getProductName() + "_" + 2, funnyRugsProduct1));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct1.getProductName() + "_" + 3, funnyRugsProduct1));

        Product funnyRugsProduct2 = productsRepository.save(new Product(UUID.randomUUID(), "MixTapeRug", funnyRugsShop, "1235UERT23", false, categoriesRepository.findByCategoryId(34L).get(),
                "Guardians of the Galaxy Mixtape. Such a good soundtrack", null, 20.00, null, null));
        Set<Attribute> attriubutesMixTapeRug = new HashSet<>();
        attriubutesMixTapeRug.add(attributeRepository.findById(49L).get());
        attriubutesMixTapeRug.add(attributeRepository.findById(55L).get());
        attriubutesMixTapeRug.add(attributeRepository.findById(32L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 20, funnyRugsProduct2.getProductId(), 20.00, false, attriubutesMixTapeRug));
        attriubutesMixTapeRug = new HashSet<>();
        attriubutesMixTapeRug.add(attributeRepository.findById(49L).get());
        attriubutesMixTapeRug.add(attributeRepository.findById(55L).get());
        attriubutesMixTapeRug.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 20, funnyRugsProduct2.getProductId(), 20.00, false, attriubutesMixTapeRug));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("teo.fudbal@gmail.com").get(), funnyRugsProduct2, "Small and applicable", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("andrej.vlaski@gmail.com").get(), funnyRugsProduct2, "Fun way to welcome your guests, worth the price", 5));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct2.getProductName() + "_" + 1, funnyRugsProduct2));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct2.getProductName() + "_" + 2, funnyRugsProduct2));

        Product funnyRugsProduct3 = productsRepository.save(new Product(UUID.randomUUID(), "ShoesOffRug", funnyRugsShop, "123SKU56U", false, categoriesRepository.findByCategoryId(34L).get(),
                "Tell your guests indirectly to take off their shoes by having this housemat", null, 18.00, null, null));
        Set<Attribute> attributesShoesOffRug = new HashSet<>();
        attributesShoesOffRug.add(attributeRepository.findById(49L).get());
        attributesShoesOffRug.add(attributeRepository.findById(55L).get());
        attributesShoesOffRug.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, funnyRugsProduct3.getProductId(), 18.00, false, attributesShoesOffRug));
        attributesShoesOffRug = new HashSet<>();
        attributesShoesOffRug.add(attributeRepository.findById(49L).get());
        attributesShoesOffRug.add(attributeRepository.findById(55L).get());
        attributesShoesOffRug.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, funnyRugsProduct3.getProductId(), 18.00, false, attributesShoesOffRug));
        attributesShoesOffRug = new HashSet<>();
        attributesShoesOffRug.add(attributeRepository.findById(49L).get());
        attributesShoesOffRug.add(attributeRepository.findById(55L).get());
        attributesShoesOffRug.add(attributeRepository.findById(38L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 15, funnyRugsProduct3.getProductId(), 18.00, false, attributesShoesOffRug));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("mario.nikoloski@gmail.com").get(), funnyRugsProduct3, "Good Product", 4));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("alek.kolarik@gmail.com").get(), funnyRugsProduct3, "Great addition. Worth the money", 5));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct3.getProductName() + "_" + 1, funnyRugsProduct3));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct3.getProductName() + "_" + 2, funnyRugsProduct3));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct3.getProductName() + "_" + 3, funnyRugsProduct3));


        Product funnyRugsProduct4 = productsRepository.save(new Product(UUID.randomUUID(), "WhaleRug", funnyRugsShop, "564RTU123", false, categoriesRepository.findByCategoryId(34L).get(),
                "Do you love whales? Look down before you get in", null, 30.00, null, null));
        Set<Attribute> attributesWhaleRug = new HashSet<>();
        attributesWhaleRug.add(attributeRepository.findById(49L).get());
        attributesWhaleRug.add(attributeRepository.findById(55L).get());
        attributesWhaleRug.add(attributeRepository.findById(40L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, funnyRugsProduct4.getProductId(), 30.00, false, attributesWhaleRug));
        attributesWhaleRug = new HashSet<>();
        attributesWhaleRug.add(attributeRepository.findById(49L).get());
        attributesWhaleRug.add(attributeRepository.findById(55L).get());
        attributesWhaleRug.add(attributeRepository.findById(39L).get());
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("ace.simonov@gmail.com").get(), funnyRugsProduct4, "Love this! Just what we needed to complete our porch!", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("filip.simonov@gmail.com").get(), funnyRugsProduct4, "Shipping took FOREVER, partly due to covid, but it also took her a long time to ship it without an explanation.", 3));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct4.getProductName() + "_" + 1, funnyRugsProduct4));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct4.getProductName() + "_" + 2, funnyRugsProduct4));

        Product funnyRugsProduct5 = productsRepository.save(new Product(UUID.randomUUID(), "ActionHeroRug", funnyRugsShop, "456UKHE12", false, categoriesRepository.findByCategoryId(34L).get(),
                "If you love action heroes, this rug will definitely make your apartment more interesting", null, 45.00, null, null));
        Set<Attribute> attributesActionHeroRug = new HashSet<>();
        attributesActionHeroRug.add(attributeRepository.findById(48L).get());
        attributesActionHeroRug.add(attributeRepository.findById(54L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, funnyRugsProduct5.getProductId(), 45.00, false, attributesActionHeroRug));
        attributesActionHeroRug = new HashSet<>();
        attributesActionHeroRug.add(attributeRepository.findById(48L).get());
        attributesActionHeroRug.add(attributeRepository.findById(55L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, funnyRugsProduct5.getProductId(), 65.00, false, attributesActionHeroRug));
        attributesActionHeroRug = new HashSet<>();
        attributesActionHeroRug.add(attributeRepository.findById(48L).get());
        attributesActionHeroRug.add(attributeRepository.findById(56L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, funnyRugsProduct5.getProductId(), 80.00, false, attributesActionHeroRug));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("leon.krstevski@gmail.com").get(), funnyRugsProduct5, "Material not that good but the children love it", 4));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("goran.krstevski@gmail.com").get(), funnyRugsProduct5, "Smaller size than it actually says but interesting", 3));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct5.getProductName() + "_" + 1, funnyRugsProduct5));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct5.getProductName() + "_" + 2, funnyRugsProduct5));
        productImagesRepository.save(new ProductImage(funnyRugsShop.getShopName() + "_" + funnyRugsProduct5.getProductName() + "_" + 3, funnyRugsProduct5));



        Product emojiClothingProduct1 = productsRepository.save(new Product(UUID.randomUUID(), "ChargersEmojiShirt",emojiClothingShop , "567345UU", false, categoriesRepository.findByCategoryId(17L).get(),
                "Smile through emojis little chargers fans", null, 17.00, null, null));
        Set<Attribute> attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(1L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(39L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(2L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(39L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(39L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(1L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(2L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(1L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(2L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        attributeEmojiShirt1 = new HashSet<>();
        attributeEmojiShirt1.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt1.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct1.getProductId(), 17.00, false, attributeEmojiShirt1));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("leon.krstevski@gmail.com").get(), emojiClothingProduct1, "Really diggin' this seller's listings. The shirt arrived in about 4 business days. Will definitely shop here again!", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("borce.amerika@gmail.com").get(), emojiClothingProduct1, "The shirt fit great and the design is straight fire. Will definitely buy again from this seller.", 5));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct1.getProductName() + "_" + 1, emojiClothingProduct1));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct1.getProductName() + "_" + 2, emojiClothingProduct1));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct1.getProductName() + "_" + 3, emojiClothingProduct1));



        Product emojiClothingProduct2 = productsRepository.save(new Product(UUID.randomUUID(), "DolphinsPrayEmojiShirt",emojiClothingShop , "5811UTE1231", false, categoriesRepository.findByCategoryId(1L).get(),
                "Praying emoji fun way to support our Miami Dolphins", null, 25.00, null, null));
        Set<Attribute> attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(38L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 25.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(4L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(38L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(5L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(38L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(6L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(38L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(4L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(5L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(6L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(4L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(5L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));
        attributeEmojiShirt2 = new HashSet<>();
        attributeEmojiShirt2.add(attributeRepository.findById(6L).get());
        attributeEmojiShirt2.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct2.getProductId(), 17.00, false, attributeEmojiShirt2));

        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("tea.tea@gmail.com").get(), emojiClothingProduct2, "Happy Fin fan!", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("ljupce.ljupce@gmail.com").get(), emojiClothingProduct2, "The shirt  is straight fire. Will definitely buy again from this seller.", 5));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("boge.boge@gmail.com").get(), emojiClothingProduct2, "Great fit excelent material and colour", 5));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct2.getProductName() + "_" + 1, emojiClothingProduct2));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct2.getProductName() + "_" + 2, emojiClothingProduct2));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct2.getProductName() + "_" + 3, emojiClothingProduct2));



        Product emojiClothingProduct3 = productsRepository.save(new Product(UUID.randomUUID(), "CoupleEmojiShirt",emojiClothingShop , "466Ui4i1", false, categoriesRepository.findByCategoryId(1L).get(),
                "Look cute and amazing as a couple", null, 20.00, null, null));
        Set<Attribute> attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 5, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(4L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(5L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(30L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(3L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(4L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        attributeEmojiShirt3 = new HashSet<>();
        attributeEmojiShirt3.add(attributeRepository.findById(5L).get());
        attributeEmojiShirt3.add(attributeRepository.findById(33L).get());
        productItemRepository.save(new ProductItem(UUID.randomUUID(), 10, emojiClothingProduct3.getProductId(), 20.00, false, attributeEmojiShirt3));
        jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("varga.car@gmail.com").get(), emojiClothingProduct3, "Cute shirts worth the price and wait", 4));
        // jpaProductReviewRepository.save(new ProductReview(usersRepository.findUserByUsername("mila.redbul@gmail.com").get(), emojiClothingProduct3, "The sticker is destroyed after one wash. Weak quality product", 1));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct3.getProductName() + "_" + 1, emojiClothingProduct3));
        productImagesRepository.save(new ProductImage(emojiClothingShop.getShopName() + "_" + emojiClothingProduct3.getProductName() + "_" + 2, emojiClothingProduct3));


        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct1",shop1 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct2",shop2 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct3",shop3 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct4",shop4 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct5",shop5 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct6",shop6 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct7",shop7 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));productsRepository.save(new Product(UUID.randomUUID(), "TestProduct1",shop1 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct8",shop8 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct9",shop9 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct10",shop10 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct11",shop11 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct12",shop12 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct13",shop13 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct14",shop14 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct15",shop15 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct16",shop16 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct17",shop17 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct18",shop18 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct19",shop19 , "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));
        productsRepository.save(new Product(UUID.randomUUID(), "TestProduct20",shop20, "111111111", false, categoriesRepository.findByCategoryId(1L).get(),
                "This is a description for a test product", null, 20.00, null, null));


        System.out.println("DATABASE INITILAIZED");




    }
}