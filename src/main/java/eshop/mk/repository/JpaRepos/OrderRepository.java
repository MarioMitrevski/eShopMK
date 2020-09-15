package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {
}
