package eshop.mk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductItemNotFoundException extends RuntimeException {
    public ProductItemNotFoundException(){
        super("This productItem does not exist");
    }
}
