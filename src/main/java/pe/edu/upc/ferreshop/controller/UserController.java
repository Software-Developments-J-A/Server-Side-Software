package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.entities.User;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
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

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProductById(@PathVariable("id") Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found User with id="+id));
        return new ResponseEntity<User>(user,HttpStatus.OK);
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


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody User user){
        User userUpdate= userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found User with id="+id));

        userUpdate.setName(user.getName());
        userUpdate.setLastname(user.getLastname());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPhone(user.getPhone());
        userUpdate.setPassword(user.getPassword());


        return new ResponseEntity<User>(userRepository.save(userUpdate),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
