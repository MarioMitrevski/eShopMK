package eshop.mk.service.serviceImpl;
import com.google.cloud.storage.*;
import eshop.mk.exceptions.ProductImagesNotSavedException;
import eshop.mk.model.ProductImage;
import eshop.mk.model.projections.ProductIdProjection;
import eshop.mk.repository.JpaRepos.ProductImagesRepository;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
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
    private Storage storage;
    private Bucket bucket;
    private int imageId;    //PRASAJ

    private static Semaphore imageSemaphore;
    private final Set<String> contentTypes;

    public ProductImagesServiceImpl(ProductImagesRepository productImagesRepository, ProductsRepositoryImpl productsRepository) {
        this.productImagesRepository = productImagesRepository;
        this.productsRepository = productsRepository;
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
    public ProductImage uploadOneProductImage(MultipartFile image, ProductIdProjection product, String shopName) throws IOException, InterruptedException {


        byte [] bytes = image.getBytes();
        System.out.println("uploadOneProductImage");

        ProductImage productImage = new ProductImage();
        String productName = product.getProductName();

        imageSemaphore.acquire();
        String imagePath = shopName + "_" + productName + "_" + imageId;
        imageId--;
        imageSemaphore.release();

        productImage.setImagePath(imagePath);
        productImage.setProduct(product.getProductId());
        Blob blob = bucket.create(imagePath,bytes,image.getContentType());

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
    public String uploadProductImages(MultipartFile[] productImagesList, UUID productId, String shopName) {
        System.out.println("uploadProductImages");
        if(productImagesList != null && productImagesList.length != 0) {
            ProductIdProjection product = productsRepository.findByProductId(productId);
            if(product.getProductId() == null){
                System.out.println("do produktot e");
                throw new ProductImagesNotSavedException();
            }

            Arrays.stream(productImagesList).parallel().forEach(image->{
                String imageContentType = image.getContentType();
                boolean notMatch = true;
                for (String cType: contentTypes) {
                    if(cType.equals(imageContentType)){
                        notMatch = false;
                        break;
                    }
                }
                if(notMatch){
                    System.out.println("do contenttypeot e");

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
            System.out.println("do ne znam so e");

            throw new ProductImagesNotSavedException();
        }
        return "Uspesno";
    }

}
