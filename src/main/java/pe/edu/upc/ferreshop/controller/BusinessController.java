package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.entities.User;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.BusinessRepository;
import pe.edu.upc.ferreshop.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class BusinessController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;


    @GetMapping("/business")
    public ResponseEntity<List<Business>> getAllBusiness() {
        List<Business> business=businessRepository.findAll();
        return new ResponseEntity<List<Business>>(business, HttpStatus.OK);
    }

    @GetMapping("/business/{userId}")
    public ResponseEntity<Business> getBusinessByUserId(@PathVariable("userId") Long userId) {

        Business businessActual= businessRepository.findBusinessByUserIdJPQL(userId);
        return new ResponseEntity<Business>(businessActual, HttpStatus.OK);
    }

    @PostMapping("/business")
    @Transactional
    public ResponseEntity<Business>save(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("description") String description,
                                        @RequestParam("brand") String brand,
                                        @RequestParam("main_img") String main_img,
                                        @RequestParam("userId") Long userId)throws IOException{
        Business newBusiness = new Business();
        newBusiness.setName(name);
        newBusiness.setEmail(email);
        newBusiness.setPhone(phone);
        newBusiness.setDescription(description);
        newBusiness.setBrand(brand);
        newBusiness.setMain_img(main_img);

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("   Not found   user with id = " + userId));
        if (user != null){
            newBusiness.setUser(user);
        }

        Business businessSaved=businessRepository.save(newBusiness);
        return new ResponseEntity<Business>(businessSaved,HttpStatus.CREATED);
    }


}
