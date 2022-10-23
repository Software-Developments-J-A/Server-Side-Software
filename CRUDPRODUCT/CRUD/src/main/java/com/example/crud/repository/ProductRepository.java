package com.example.crud.repository;

import com.example.crud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM products WHERE  status=?1", nativeQuery = true)
    List<Product> findByStatusSQL(boolean status);

    @Query("SELECT p FROM Product p WHERE p.status=?1")
    List<Product> findByStatusJPQL(boolean status);

    List<Product> findByStatus(boolean status);

    List<Product> findByNameContaining (String name);
}
