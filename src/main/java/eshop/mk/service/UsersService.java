package eshop.mk.service;

import eshop.mk.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService extends UserDetailsService {

    User createUser(User user);
    Optional<User> getUser(UUID userId);
    List<User> getAllUsers();
}
