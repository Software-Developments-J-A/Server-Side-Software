package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Product;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,  Long> {

    List<Product> findByName(String title);

   // @Query(value="SELECT * FROM products p INNER JOIN users u on p.user_id = u.id WHERE u.id=?1",nativeQuery = true)
    //List<Product> findAllProductsUserIdSQL(Long userId);

}
