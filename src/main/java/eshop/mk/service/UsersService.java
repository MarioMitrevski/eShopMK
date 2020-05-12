package eshop.mk.service;

import eshop.mk.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {


    User createUser(User user);
    Optional<User> getUser(UUID userId);

    List<User> getAllUsers();
}
