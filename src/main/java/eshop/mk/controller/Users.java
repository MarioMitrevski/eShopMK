package eshop.mk.controller;
import eshop.mk.jwt.UsernameAndPasswordAuthenticationRequest;
import eshop.mk.model.User;
import eshop.mk.service.UsersService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
