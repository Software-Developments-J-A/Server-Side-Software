package pe.edu.upc.ferreshop.controller;


import org.hibernate.boot.archive.scan.spi.ClassDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.entities.Category;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.entities.User;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.BusinessRepository;
import pe.edu.upc.ferreshop.repository.CategoryRepository;
import pe.edu.upc.ferreshop.repository.ProductRepository;
import pe.edu.upc.ferreshop.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CategoryController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> searchCategories() {
        List<Category> categories=categoryRepository.findAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> searchCategoriesById(@PathVariable Long id) {
        Category category= categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found category with id="+id));
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category newCategory= Category.builder().name(category.getName()).build();
        categoryRepository.save(newCategory);
        return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        Category categoryUpdate= categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found category with id="+id));

        categoryUpdate.setName(category.getName());

        return new ResponseEntity<Category>(categoryRepository.save(categoryUpdate),
                HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
