package eshop.mk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "Attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long attributeId;
    private String attributeName;  //SIZE (xs, s,m,l,xl,xxl), LENGTH (), MATERIAL, COLOR
    private String attributeValue;


    public Attribute(String attributeName, String attributeValue){
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }




}
