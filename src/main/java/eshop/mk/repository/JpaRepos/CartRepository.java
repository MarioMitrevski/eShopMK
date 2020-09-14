package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findCartByCartId(UUID uuid);
}
