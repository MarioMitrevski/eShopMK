package eshop.mk.controller;

import eshop.mk.model.Attribute;
import eshop.mk.service.AttributesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.100.16:3000")
@RequestMapping(path = "/api/attributes")
public class AttributesController {

    private final AttributesService attributesService;

    public AttributesController(AttributesService attributesService) {
        this.attributesService = attributesService;
    }

    @GetMapping(path = "/all")
    public List<Attribute> getAllAttributes() {
        return attributesService.getAllAttributes();
    }
}
