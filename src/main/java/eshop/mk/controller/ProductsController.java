package eshop.mk.controller;


import eshop.mk.model.Page;
import eshop.mk.model.Product;
import eshop.mk.model.modelDTOS.ProductCreationDTO;
import eshop.mk.model.modelDTOS.ProductForMainPageDTO;
import eshop.mk.model.modelDTOS.ProductItemCreationDTO;
import eshop.mk.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
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
                                               @RequestParam(name = "page-size", defaultValue = "20", required = false) int size,
                                               @RequestParam(name = "sort", defaultValue = "created_date",required = false) String sort,
                                               @RequestParam(name = "categoryId") Long categoryId) {

        return productsService.getProductsByCategory(page, size, sort, categoryId);

    }

}
