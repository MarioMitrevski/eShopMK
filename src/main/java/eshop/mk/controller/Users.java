package eshop.mk.controller;

import eshop.mk.model.Role;
import eshop.mk.model.User;
import eshop.mk.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/users")
public class Users {

    private final UsersService usersService;

    public Users(UsersService usersService) {
        this.usersService = usersService;
    }


    @PostMapping(path = "/createUser")
    public User createUserAccount(@RequestBody User user){

        return usersService.createUser(user);
    }

    @GetMapping(path = "/getUsers")
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }


/*    @GetMapping(path = "/getUser")
    public User getUserAccount(@RequestParam UUID userId){
        return usersService.getUser(userId);
    }*/
}
