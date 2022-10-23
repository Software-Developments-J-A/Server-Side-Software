package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Product;
//import pe.edu.upc.ferreshop.entities.User;
//import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.ProductRepository;
//import pe.edu.upc.ferreshop.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts () {
        List<Product>products=productRepository.findAll();
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable("id")Long id){
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product found with id: "+id));

        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    //@GetMapping("/products")
    //public ResponseEntity<List<Product>>getAllProducts() {
      //  List<Product>products=productRepository.findAll();
        //return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
   // }

    //@GetMapping("/users/{userId}/products")
    //public ResponseEntity<List<Product>>getAllProductsByUserId(@PathVariable("userId")Long userId) {
      //  if (!userRepository.existsById(userId)) {
        //    throw new ResourceNotFoundException("No existe un usuario con el Id: "+userId);
        //}
        //List<Product> products = productRepository.findAllProductsUserIdSQL(userId);
        //return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
   // }




}
