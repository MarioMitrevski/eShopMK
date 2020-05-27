package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.UserEmailAlreadyExistsException;
import eshop.mk.exceptions.UserTableNotSavedException;
import eshop.mk.model.Role;
import eshop.mk.model.User;
import eshop.mk.model.projections.UserEmailsProjection;
import eshop.mk.repository.JpaRepos.RolesRepository;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.security.UserDetailsImpl;
import eshop.mk.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public User createUser(User user) throws UserEmailAlreadyExistsException, UserTableNotSavedException {

        List<UserEmailsProjection> emails = usersRepository.findAllBy(); //Get emails from all users
        boolean emailExists = emails.stream().anyMatch(p ->p.getUsername().equals(user.getUsername()));

        if(emailExists){
            throw new UserEmailAlreadyExistsException();

        }
        List<Role> roles = user.getRoles();
        Role role = rolesRepository.findByName("USER");
       if(role != null){
            roles.add(role);
        }else{
            throw new UserTableNotSavedException();
        }


        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepository.save(user);
        } catch (Exception ex){
            throw new UserTableNotSavedException();
        }

        return user;
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = usersRepository
                .findUserByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", s))
                );
        return new UserDetailsImpl(user);
    }
}
