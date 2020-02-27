package eshop.mk.model;

import lombok.AllArgsConstructor;

import lombok.Getter;


@AllArgsConstructor
@Getter
public enum StatusOrder {

    P("Pending"),
    S("Shipping"),
    D("Done");

    private String description;


}
