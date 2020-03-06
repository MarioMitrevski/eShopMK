package eshop.mk.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor

public enum StatusOrder {

      P("Pending"),
      S("Shipping"),
      D("Done");

    private String description;

}
