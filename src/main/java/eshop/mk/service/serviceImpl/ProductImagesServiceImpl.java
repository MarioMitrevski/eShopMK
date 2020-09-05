package eshop.mk.service.serviceImpl;

import com.google.cloud.storage.*;
import eshop.mk.exceptions.ProductImagesNotSavedException;
import eshop.mk.exceptions.ShopNotFoundException;
import eshop.mk.exceptions.ShopTableNotSavedException;
import eshop.mk.exceptions.UserNotFoundException;
import eshop.mk.model.Product;
import eshop.mk.model.ProductImage;
import eshop.mk.model.Shop;
import eshop.mk.model.User;
import eshop.mk.repository.JpaRepos.ProductImagesRepository;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
import eshop.mk.repository.repositoryImpl.ShopsRepositoryImpl;
import eshop.mk.service.ProductImagesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    private final ProductImagesRepository productImagesRepository;
    private final ProductsRepositoryImpl productsRepository;
    private final ShopsRepositoryImpl shopsRepository;
    private final UsersServiceImpl usersService;
    private Storage storage;
    private Bucket bucket;
    private int imageId;

    private static Semaphore imageSemaphore;
    private final Set<String> contentTypes;

    public ProductImagesServiceImpl(ProductImagesRepository productImagesRepository,
                                    ProductsRepositoryImpl productsRepository,
                                    ShopsRepositoryImpl shopsRepository,
                                    UsersServiceImpl usersService) {
        this.productImagesRepository = productImagesRepository;
        this.productsRepository = productsRepository;
        this.shopsRepository = shopsRepository;
        this.usersService = usersService;
        this.contentTypes = new HashSet<>();
        this.contentTypes.add("image/png");
        this.contentTypes.add("image/jpeg");
        this.contentTypes.add("image/jpg");
        storage = StorageOptions.getDefaultInstance().getService();
        bucket = getBucket("eshopmk-78147.appspot.com");
        imageId = 0;
        imageSemaphore = new Semaphore(1);
    }

    @Override
    public ProductImage uploadOneProductImage(MultipartFile image,
                                              Product product,
                                              String shopName) throws IOException, InterruptedException {

        byte[] bytes = image.getBytes();
        System.out.println("uploadOneProductImage");

        ProductImage productImage = new ProductImage();
        String productName = product.getProductName();

        imageSemaphore.acquire();
        String imagePath = shopName + "_" + productName + "_" + imageId;
        imageId--;
        imageSemaphore.release();

        productImage.setImagePath(imagePath);
        productImage.setProduct(product);
        Blob blob = bucket.create(imagePath, bytes, image.getContentType());

        return productImagesRepository.save(productImage);
    }

    @Override
    public URL downloadProductImage(String imageBlob) {

        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket.getName(), imageBlob)).build();
        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        return url;
    }

    @Override
    public List<URL> getProductImages(List<String> productImagesPaths) {
        return productImagesPaths.parallelStream().map(this::downloadProductImage).collect(Collectors.toList());
    }

    // Check for bucket existence and create if needed.
    private Bucket getBucket(String bucketName) {
        bucket = storage.get(bucketName);
        return bucket;
    }

    @Override
    public void uploadProductImages(MultipartFile[] productImagesList,
                                    UUID productId,
                                    String shopName) {
        if (productImagesList != null && productImagesList.length != 0) {
            Product product = productsRepository.findByProductId(productId);
            if (product.getProductId() == null) {
                throw new ProductImagesNotSavedException();
            }
            Arrays.stream(productImagesList).parallel().forEach(image -> {
                String imageContentType = image.getContentType();
                boolean notMatch = true;
                for (String cType : contentTypes) {
                    if (cType.equals(imageContentType)) {
                        notMatch = false;
                        break;
                    }
                }
                if (notMatch) {
                    throw new ProductImagesNotSavedException();
                }
            });
            imageId = productImagesList.length;
            Arrays.stream(productImagesList).parallel().forEach(image -> {

                try {
                    uploadOneProductImage(image, product, shopName);
                } catch (IOException | InterruptedException e) {
                    throw new ProductImagesNotSavedException();
                }
            });
        } else {
            throw new ProductImagesNotSavedException();
        }
    }

    @Override
    public void uploadShopImage(MultipartFile image,
                                UUID userId,
                                UUID shopId) throws IOException, InterruptedException {
        byte[] bytes = image.getBytes();

        User user = usersService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Shop shop = shopsRepository.getShop(shopId).orElseThrow(ShopNotFoundException::new);

        if (!user.getShop().equals(shop.getShopId())) {
            throw new ShopTableNotSavedException();
        }
        Blob blob = bucket.create(shop.getShopName(), bytes, image.getContentType());
    }

    @Override
    public URL downloadShopImage(String imageBlob) {
        //TODO CHANGE IN imageBlob
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket.getName(), "HappyShop")).build();
        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        return url;
    }
}
