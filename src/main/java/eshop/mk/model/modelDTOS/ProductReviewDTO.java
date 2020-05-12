package eshop.mk.model.modelDTOS;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ProductReviewDTO {

    String firstName;
    String lastName;
    String comment;
    int grade;
    LocalDateTime createdDate;


    public ProductReviewDTO(String firstName,String lastName,String comment,int grade,LocalDateTime createdDate){
        this.comment=comment;
        this.createdDate=createdDate;
        this.grade=grade;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
