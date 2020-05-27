package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Shop;
import eshop.mk.model.User;
import eshop.mk.model.projections.UserEmailsProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository  extends JpaRepository<User, Integer> {

    Optional<User> findByUserId(UUID userId);


    List<UserEmailsProjection> findAllBy();


    Optional<User> findByUserIdAndShop(UUID userId, UUID shop);


    Optional<User> findUserByUsername(String username);

}
