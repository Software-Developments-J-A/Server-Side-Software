package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Category;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.BusinessRepository;
import pe.edu.upc.ferreshop.repository.CategoryRepository;
import pe.edu.upc.ferreshop.repository.ProductRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BusinessRepository businessRepository;

    private CategoryRepository categoryRepository;

    public ProductController(CategoryRepository categoryRepository,ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @PostMapping("/products")
    @Transactional
    public ResponseEntity<Product> save(@RequestParam("name") String name,
                                        @RequestParam("Summary") String summary,
                                        @RequestParam("brand") String brand,
                                        @RequestParam("quantity") Long quantity,
                                        @RequestParam("price") Long price,
                                        @RequestParam("status") boolean status,
                                        @RequestParam("categoryId") Long categoryID)throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setSummary(summary);
        product.setBrand(brand);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setStatus(status);

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResourceNotFoundException("Not found category with id="+categoryID));

        if( category!=null) {
            product.setCategory(category);
        }

        Product productSaved=productRepository.save(product);

        return new ResponseEntity<Product>(productSaved,HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/business/{businessId}/products")
    public ResponseEntity<List<Product>> getAllProductByBusiness(@PathVariable("businessId") Long businessId){
        if(!businessRepository.existsById(businessId)){
            throw new ResourceNotFoundException("No existe tutorial con el id="+businessId);
        }
        List<Product> products= productRepository.findAllProductBusinessIdJPQL(businessId);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+id));
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){

        Product newProduct=
                productRepository.save(
                        new Product(
                                product.getName(),
                                product.getSummary(),
                                product.getBrand(),
                                product.getQuantity(),
                                product.getPrice(),
                                product.isStatus(),
                                product.getBusiness(),
                                product.getCategory()));

        return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product){
        Product productUpdate= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+id));

        productUpdate.setName(product.getName());
        productUpdate.setSummary(product.getSummary());
        productUpdate.setBrand(product.getBrand());
        productUpdate.setQuantity(product.getQuantity());
        productUpdate.setPrice(product.getPrice());
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

    @GetMapping("/products/quantity/{quantity}")
    public  ResponseEntity<List<Product>> findProductByQuantity(@PathVariable("quantity") Long quantity){
        List<Product> products=productRepository.findByQuantitySQL(quantity);
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/price/{price}")
    public  ResponseEntity<List<Product>> findProductByPrice(@PathVariable("price") Long price){
        List<Product> products=productRepository.findByPriceSQL(price);
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
