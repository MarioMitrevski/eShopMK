package eshop.mk.service;

import eshop.mk.model.Attribute;

import java.util.List;

public interface AttributesService {

    List<Attribute> getAllAttributes();
    Attribute save(Attribute attribute);
}
