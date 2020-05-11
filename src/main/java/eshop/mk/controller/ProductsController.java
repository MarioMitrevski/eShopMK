package eshop.mk.controller;

import eshop.mk.model.Page;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductDTO;
import eshop.mk.model.modelDTOS.ProductDetailsDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.projections.ProductsForMainPageProjection;
import eshop.mk.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }



    @PostMapping(path = "/post")
    public String createProduct(@RequestBody ProductCreationDTO productCreationDTO) {

        return productsService.createProduct(productCreationDTO);

    }

  /*  @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/


    @GetMapping
    public Page<ProductForMainPageDTO> getProductsByCategory(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                       @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                                                       @RequestParam(name = "sortBy", defaultValue = "product_name",required = false) String sort,
                                                                       @RequestParam(name = "order", defaultValue = "ASC") String order,
                                                                       @RequestParam(name = "categoryId",required = false) Long categoryId) {

        return productsService.getProducts(page, size, sort,order, categoryId);

    }


    @GetMapping(path = "getProduct")
    public ProductDetailsDTO getProduct(@RequestParam(name = "id") UUID productId){
        return productsService.getProduct(productId);
    }
}
