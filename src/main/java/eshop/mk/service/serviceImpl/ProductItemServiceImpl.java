package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.ProductTableNotSavedException;
import eshop.mk.model.Attribute;
import eshop.mk.model.Product;
import eshop.mk.model.ProductItem;
import eshop.mk.model.modelDTOS.ProductItemCreationDTO;
import eshop.mk.repository.JpaRepos.ProductItemRepository;
import eshop.mk.repository.repositoryImpl.ProductsRepositoryImpl;
import eshop.mk.service.ProductItemService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final ProductsRepositoryImpl productsRepository;
    private final AttributesServiceImpl attributesService;

    public ProductItemServiceImpl(ProductItemRepository productItemRepository, ProductsRepositoryImpl productsRepository, AttributesServiceImpl attributesService) {
        this.productItemRepository = productItemRepository;
        this.productsRepository = productsRepository;
        this.attributesService = attributesService;
    }

    @Transactional
    @Override
    public Product createProductItems(Product product, List<ProductItemCreationDTO> productItemCreationDTOS) {

            List<Attribute> allAttributes = attributesService.getAllAttributes();
            Double minPrice = productItemCreationDTOS.stream().mapToDouble(ProductItemCreationDTO::getPrice).min().getAsDouble();
            List<ProductItem> productItems =productItemCreationDTOS.stream().map(productItemDto -> {

                ProductItem newProductItem = new ProductItem();
                newProductItem.setDeleted(false);
                newProductItem.setQuantityInStock(productItemDto.getQuantity());
                newProductItem.setPrice(productItemDto.getPrice());
                Set<Attribute> productItemAttributes = newProductItem.getAttributes();

                for(int i =0;i<productItemDto.getProductItemAttributes().size();i++){

                    boolean notExists = true;
                    boolean newAttr = false;
                    String dtoAttrName = productItemDto.getProductItemAttributes().get(i).getAttributeName();
                    String dtoAttrValue = productItemDto.getProductItemAttributes().get(i).getAttributeValue();
                    if(dtoAttrName!= null && dtoAttrValue!= null){

                    for (Attribute allAttribute : allAttributes) {

                        String attributeName = allAttribute.getAttributeName();
                        String attributeValue = allAttribute.getAttributeValue();
                        if (attributeName.equals(dtoAttrName)) {
                            newAttr = true;
                            notExists = false;

                            if (attributeValue.equals(dtoAttrValue)) {
                                productItemAttributes.add(allAttribute);
                                newAttr = false;
                                break;
                            }
                        }
                        }
                    }

                    if(newAttr){
                        Attribute attribute = new Attribute();
                        attribute.setAttributeName(dtoAttrName);
                        attribute.setAttributeValue(dtoAttrValue);
                        attribute = attributesService.save(attribute);
                        productItemAttributes.add(attribute);
                    }
                   if(notExists){
                       throw new ProductTableNotSavedException();
                   }
                }
                return newProductItem;

            }).collect(Collectors.toList());

            product.setPrice(minPrice);
            productsRepository.save(product);

            for(ProductItem productItem:productItems){
                productItem.setProduct(product.getProductId());
                productItemRepository.save(productItem);
            }

            return product;
    }

    @Override
    public List<ProductItem> getProductItems(UUID productId) {
        return productItemRepository.findAllByProductAndDeletedFalse(productId);
    }
}
