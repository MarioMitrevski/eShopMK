package eshop.mk.repository;

import eshop.mk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {


    Role findByName(String name);
}
