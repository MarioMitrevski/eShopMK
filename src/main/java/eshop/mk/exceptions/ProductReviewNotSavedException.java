package eshop.mk.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductReviewNotSavedException extends RuntimeException{

    public ProductReviewNotSavedException(String message){
        super(message);
    }
}
