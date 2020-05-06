package eshop.mk.service.serviceImpl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import eshop.mk.exceptions.ProductImagesNotSavedException;
import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.model.Product;
import eshop.mk.model.ProductImage;
import eshop.mk.repository.ProductImagesRepository;
import eshop.mk.repository.ProductsRepository;
import eshop.mk.service.ProductImagesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;


@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    private final ProductImagesRepository productImagesRepository;
    private final ProductsRepository productsRepository;
    private Storage storage;
    private Bucket bucket;
    private int imageId;    //PRASAJ

    private static Semaphore imageSemaphore;
    private final Set<String> contentTypes;

    public ProductImagesServiceImpl(ProductImagesRepository productImagesRepository, ProductsRepository productsRepository) {
        this.productImagesRepository = productImagesRepository;
        this.productsRepository = productsRepository;
        this.contentTypes = new HashSet<>();
        this.contentTypes.add("image/png");
        this.contentTypes.add("image/jpeg");
        this.contentTypes.add("image/jpg");

        storage = StorageOptions.getDefaultInstance().getService();

        /*Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\User\\Downloads\\eShopMK-99330a35d1ba.json"));
        storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("eshopmk-78147").build().getService();*/
        bucket = getBucket("eshopmk-78147.appspot.com");

        imageId = 0;
        imageSemaphore = new Semaphore(1);

    }


    @Override
    public ProductImage uploadOneProductImage(MultipartFile image, Product product, String shopName) throws IOException, InterruptedException {


        byte [] bytes = image.getBytes();
        System.out.println("uploadOneProductImage");

        ProductImage productImage = new ProductImage();
        String productName = product.getProductName();

        imageSemaphore.acquire();
        String imagePath = shopName + "_" + productName + "_" + imageId;
        imageId--;
        imageSemaphore.release();

        productImage.setImagePath(imagePath);
        productImage.setProduct(product);
        Blob blob = bucket.create(imagePath,bytes,image.getContentType());

        return productImagesRepository.save(productImage);
    }


    // Check for bucket existence and create if needed.
    private Bucket getBucket(String bucketName) {
        bucket = storage.get(bucketName);
        return bucket;
    }



    @Override
    public String uploadProductImages(MultipartFile[] productImagesList, UUID productId, String shopName) {
        System.out.println("uploadProductImages");
        if(productImagesList != null && productImagesList.length != 0) {
            Product product = productsRepository.findByProductId(productId);
            if(product == null){
                throw new ProductImagesNotSavedException();
            }

            Arrays.stream(productImagesList).forEach(image->{
                String imageContentType = image.getContentType();
                boolean notMatch = true;
                for (String cType: contentTypes) {
                    if(cType.equals(imageContentType)){
                        notMatch = false;
                        break;
                    }
                }
                if(notMatch){
                    throw new ProductImagesNotSavedException();
                }
            });


            imageId = productImagesList.length;


            Arrays.stream(productImagesList).forEach(image -> {

                try {
                    uploadOneProductImage(image, product, shopName);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }else{
            throw new ProductImagesNotSavedException();
        }
        return "Uspesno";
    }
    //Ako ne uspee postavuvanje na Cloud ideme so FileSystem resenie

        /*ProductImage productImage = new ProductImage();
        productImage.setProduct(product);

        Path currentPath = Paths.get(".").toAbsolutePath();
        String absolutePath = currentPath + "/src/main/resources/static/photos/" ;
        Path path = Files.createDirectories(Paths.get(absolutePath  + "/" + shopName));
        byte[] bytes = image.getBytes();

        productImage.setImagePath(product.getProductName());
        productImagesRepository.save(productImage);

        Path finalPath = Paths.get(path + "/" + productImage.getProductImageId() + ".jpg");
        Files.write(finalPath, bytes);*/
}
