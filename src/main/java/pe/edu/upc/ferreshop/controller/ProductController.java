package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.entities.Category;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.export.Export;
import pe.edu.upc.ferreshop.export.ProductExcelExporter;
import pe.edu.upc.ferreshop.repository.BusinessRepository;
import pe.edu.upc.ferreshop.repository.CategoryRepository;
import pe.edu.upc.ferreshop.repository.ProductRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
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
    public ResponseEntity<Product> save(@RequestParam("brand") MultipartFile brand,
                                        @RequestParam("name") String name,
                                        @RequestParam("summary") String summary,
                                        @RequestParam("price") Long price,
                                        @RequestParam("quantity") Long quantity,
                                        @RequestParam("status") boolean status,
                                        @RequestParam("category_id") Long categoryID,
                                        @RequestParam("business_id") Long businessID)throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setSummary(summary);
        product.setBrand(Export.compressZLib(brand.getBytes()));
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setStatus(status);

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResourceNotFoundException("Not found category with id="+categoryID));

        if( category!=null) {
            product.setCategory(category);
        }

        Business business = businessRepository.findById(businessID)
                .orElseThrow(()-> new ResourceNotFoundException("Not found category with id="+businessID));

        if( business!=null) {
            product.setBusiness(business);
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
            throw new ResourceNotFoundException("No existe Business con el id="+businessId);
        }
        List<Product> products= productRepository.findAllProductBusinessIdJPQL(businessId);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/shop/{businessName}")
    public ResponseEntity<List<Product>> getAllProductByBusinessName(@PathVariable("businessName") String businessName){
        List<Product> products= productRepository.findAllProductByBusinessNameJPQL(businessName);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found product with id="+id));
        return new ResponseEntity<Product>(product,HttpStatus.OK);
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



    @GetMapping("/products/filter/{name}")
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ResponseEntity<List<Product>> searchByName(@PathVariable String name){
        List<Product> products=new ArrayList<>();
        List<Product> productsAux=new ArrayList<>();

        productsAux=productRepository.findByNameContainingIgnoreCase(name);

        if(productsAux.size()>0){
            productsAux.stream().forEach((p)->{
                byte[] imageDescompressed = Export.decompressZLib(p.getBrand());
                p.setBrand(imageDescompressed);
                products.add(p);
            });
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
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

    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_product";
        response.setHeader(headerKey, headerValue);

        List<Product> products = productRepository.findAll();

        ProductExcelExporter excelExporter = new ProductExcelExporter(
                products);

        excelExporter.export(response);
    }



}
