package eshop.mk.repository.JpaRepos;

import eshop.mk.model.Shop;
import eshop.mk.model.User;
import eshop.mk.model.modelDTOS.TestUserDTO;
import eshop.mk.model.projections.TestUserProjection;
import eshop.mk.model.projections.UserEmailsProjection;
import eshop.mk.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsersRepository  extends JpaRepository<User, Integer> {


    @EntityGraph(attributePaths = "roles")
    User findByUserId(UUID userId);


/*
    //@EntityGraph(attributePaths = {"shop"})
    @Query("select u.userId,shop from User u left join fetch u.shop shop")
    List<TestUserProjection> getAllBy();

*/




    List<UserEmailsProjection> findAllBy();


    User findByUserIdAndShop(UUID userId, Shop shop);

    //@Query("select u.firstName,u.lastName from User u")
    List<UserProjection> findUserByFirstNameAndLastName(String firstName,String lastName);
}
