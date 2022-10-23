package com.example.crud.controller;


import com.example.crud.entities.Product;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+id));


        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct( @RequestBody Product product){
        Product newProduct=
                productRepository.save(
                        new Product(product.getName(),
                                product.getSummary(),
                                product.getBrand(),
                                product.isStatus())
                );
        return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
    }


    //PUT=>http:localthost:8080/api/users/1
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product){
        Product productUpdate= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+id));

        productUpdate.setName(product.getName());
        productUpdate.setSummary(product.getSummary());
        productUpdate.setBrand(product.getBrand());
        productUpdate.setStatus(product.isStatus());


        return new ResponseEntity<Product>(productRepository.save(productUpdate),HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/products/status")
    public  ResponseEntity<List<Product>> findProductByStatus(){
        List<Product> products=productRepository.findByStatusSQL(true);
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
