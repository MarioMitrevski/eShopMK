package eshop.mk.controller;

import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductDetailsDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.service.ProductsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }



    @PostMapping(path = "/create")
    @PreAuthorize("hasAnyRole('ROLE_SHOPMANAGER','ROLE_SALES')")
    public UUID createProduct(@RequestBody ProductCreationDTO productCreationDTO) {

        return productsService.createProduct(productCreationDTO);

    }


    @GetMapping(path = "/all")
    public Page<ProductForMainPageDTO> getProductsByCategory(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                             @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                                             @RequestParam(name = "sortBy", defaultValue = "created_date",required = false) String sort,
                                                             @RequestParam(name = "order", defaultValue = "DESC",required = false) String order,
                                                             @RequestParam(name = "categoryId",required = false) Long categoryId) {

        return productsService.getProducts(page, size, sort,order, categoryId);
    }


    @GetMapping(path = "/{product}")
    public ProductDetailsDTO getProduct(@PathVariable(name = "product") UUID productId){
        return productsService.getProduct(productId);
    }

    @GetMapping(path = "/shop/{shopId}")
    public Page<ProductForMainPageDTO> getProductsForShop(@PathVariable(name = "shopId") UUID shopId,
                                                          @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                                          @RequestParam(name = "sortBy", defaultValue = "created_date",required = false) String sort,
                                                          @RequestParam(name = "order", defaultValue = "DESC",required = false) String order,
                                                          @RequestParam(name = "categoryId",required = false) Long categoryId){
        if(categoryId!=null){

            return productsService.getProductsFromShopByCategory(page,size,sort,order,categoryId,shopId);
        }else{
            return productsService.getProductsFromShop(page,size,sort,order,shopId);

        }
    }

}
