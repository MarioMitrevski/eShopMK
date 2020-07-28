package eshop.mk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrder {

      P("Pending"),
      S("Shipping"),
      D("Done");

    private String description;
}
