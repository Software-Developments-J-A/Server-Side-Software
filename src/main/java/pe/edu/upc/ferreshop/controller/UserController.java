package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.User;
import pe.edu.upc.ferreshop.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public ResponseEntity<List<User>>getAllUsers() {
        List<User>users=userRepository.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User>createUser(@RequestBody User user) {
        User newUser=
                userRepository.save(
                        new User(user.getName(),
                                user.getLastname(),
                                user.getPhone(),
                                user.getEmail(),
                                user.getPassword()
                                )
                );
        return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
    }
}
