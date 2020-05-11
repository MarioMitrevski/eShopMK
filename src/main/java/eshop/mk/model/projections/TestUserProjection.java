package eshop.mk.model.projections;


import eshop.mk.model.Shop;

import java.util.UUID;

public interface TestUserProjection {

    UUID getUserId();
    Shop getShop();
}
