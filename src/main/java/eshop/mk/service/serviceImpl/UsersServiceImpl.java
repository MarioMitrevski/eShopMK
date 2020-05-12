package eshop.mk.service.serviceImpl;

import eshop.mk.exceptions.UserEmailAlreadyExistsException;
import eshop.mk.exceptions.UserTableNotSavedException;
import eshop.mk.model.Role;
import eshop.mk.model.User;
import eshop.mk.model.projections.UserEmailsProjection;
import eshop.mk.repository.JpaRepos.RolesRepository;
import eshop.mk.repository.JpaRepos.UsersRepository;
import eshop.mk.service.UsersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }


    @Transactional
    @Override
    public User createUser(User user) throws UserEmailAlreadyExistsException, UserTableNotSavedException {

        List<UserEmailsProjection> emails = usersRepository.findAllBy(); //Get emails from all users
        boolean emailExists = emails.stream().anyMatch(p ->p.getEmail().equals(user.getEmail()));

        if(emailExists){
            throw new UserEmailAlreadyExistsException();

        }
        List<Role> roles = user.getRoles();
        Role role = rolesRepository.findByName("ROLE_USER");
        if(role != null){
            roles.add(role);
        }else{
            throw new UserTableNotSavedException();
        }

        try{
            usersRepository.save(user);
        } catch (Exception ex){
            throw new UserTableNotSavedException();
        }
        return user;

    }

    @Override
    public User getUser(UUID userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
