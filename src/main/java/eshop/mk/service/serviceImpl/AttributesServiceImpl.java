package eshop.mk.service.serviceImpl;

import eshop.mk.model.Attribute;
import eshop.mk.repository.JpaRepos.AttributeRepository;
import eshop.mk.service.AttributesService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttributesServiceImpl implements AttributesService {

    private final AttributeRepository attributeRepository;

    public AttributesServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }
}
