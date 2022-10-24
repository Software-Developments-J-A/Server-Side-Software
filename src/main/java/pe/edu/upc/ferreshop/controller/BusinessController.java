package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.BusinessRepository;
import pe.edu.upc.ferreshop.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;


    @GetMapping("/business")
    public ResponseEntity<List<Business>> getAllBusiness() {
        List<Business> business=businessRepository.findAll();
        return new ResponseEntity<List<Business>>(business, HttpStatus.OK);
    }

    @PostMapping("/business")
    public ResponseEntity<Business>createBusiness(@RequestBody Business business) {
        Business newBusiness=
                businessRepository.save(
                        new Business(business.getName(),
                                business.getEmail(),
                                business.getPhone(),
                                business.getDescription(),
                                business.getBrand(),
                                business.getMain_img(),
                                business.getUser()
                        )
                );
        return new ResponseEntity<Business>(newBusiness,HttpStatus.CREATED);
    }
}
