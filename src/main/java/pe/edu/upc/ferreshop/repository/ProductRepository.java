package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Product;

import java.util.List;

public interface ProductRepository    extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM products WHERE  status=?1", nativeQuery = true)
    List<Product> findByStatusSQL(boolean status);

    @Query(value="SELECT * FROM products WHERE  quantity=50", nativeQuery = true)
    List<Product> findByQuantitySQL(Long quantity);

    @Query(value="SELECT * FROM products WHERE  price=10", nativeQuery = true)
    List<Product> findByPriceSQL(Long price);

    @Query("SELECT p FROM Product p WHERE p.status=?1")
    List<Product> findByStatusJPQL(boolean status);

    List<Product> findByStatus(boolean status);

    List<Product> findByNameContaining (String name);
}
