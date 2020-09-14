package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductDTO;
import eshop.mk.model.projections.ProductIdProjection;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;
import java.util.UUID;

public interface JpaProductsRepository extends JpaRepository<Product, Long> {

    Product findByProductIdAndDeleted(UUID productID, Boolean deleted);

    //@EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "productImages")
    Product findProductByProductIdAndDeleted(UUID productId, Boolean deleted);

    Product findByProductId(UUID uuid);

    @Query("select new eshop.mk.model.modelDTOS.ProductDTO(p.productId,p.productName,p.productDescription,p.price,pI.imagePath) from Product p join p.productImages as pI on p.productId=pI.product.productId where p.productId=:productId and p.deleted=false")
    List<ProductDTO> findProductByProductId(UUID productId);

    @Query(value = "select  BIN_TO_UUID(p.product_id) as ProductId, p.product_name as ProductName,p.price as Price,p.product_description as ProductDescription,pI.image_path as ImagePath,BIN_TO_UUID(p.shop_shop_id)  as ShopId from product_images as pI join products as p on p.product_id = pI.product_product_id where p.product_category_category_id in ?2 and pI.image_path like '%1' and p.deleted = ?1",
            countQuery = "select count(*) from products  join product_images on products.product_id = product_images.product_product_id where products.product_category_category_id in ?2 and product_images.image_path like '%1' and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPageByCategory(Boolean deleted, List<Long> categorySubcategories, Pageable pageable);


    @Query(value = "select  BIN_TO_UUID(p.product_id) as ProductId, p.product_name as ProductName,p.price as Price,p.product_description as ProductDescription,pI.image_path as ImagePath,BIN_TO_UUID(p.shop_shop_id) as ShopId from product_images as pI join products as p on p.product_id = pI.product_product_id where pI.image_path like '%1' and p.deleted = ?1 ",
            countQuery = "select count(*) from products join product_images on products.product_id = product_images.product_product_id where product_images.image_path like '%1' and products.deleted = :deleted ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPage(Boolean deleted, Pageable pageable);

    //Queries for products from Shop

    @Query(value = "select  BIN_TO_UUID(p.product_id) as ProductId, p.product_name as ProductName,p.price as Price,p.product_description as ProductDescription,pI.image_path as ImagePath,BIN_TO_UUID(p.shop_shop_id)  as ShopId from product_images as pI join products as p on p.product_id = pI.product_product_id where p.product_category_category_id in ?2  and p.shop_shop_id=?3 and pI.image_path like '%1' and p.deleted = ?1",
            countQuery = "select count(*) from products join product_images on products.product_id = product_images.product_product_id where products.product_category_category_id in ?2 and products.shop_shop_id=?3 and product_images.image_path like '%1' and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShopByCategory(Boolean deleted, List<Long> categorySubcategories, UUID shopId, Pageable pageable);

    @Query(value = "select  BIN_TO_UUID(p.product_id) as ProductId, p.product_name as ProductName,p.price as Price,p.product_description as ProductDescription,pI.image_path as ImagePath,BIN_TO_UUID(p.shop_shop_id)  as ShopId from product_images as pI join products as p on p.product_id = pI.product_product_id where p.shop_shop_id=?2 and pI.image_path like '%1' and p.deleted = ?1 ",
            countQuery = "select count(*) from products join product_images on products.product_id = product_images.product_product_id where products.shop_shop_id=?2 and product_images.image_path like '%1' and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShop(Boolean deleted, UUID shopId, Pageable pageable);
}
