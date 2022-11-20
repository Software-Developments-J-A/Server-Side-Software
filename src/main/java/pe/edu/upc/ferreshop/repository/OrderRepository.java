package pe.edu.upc.ferreshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Order;
import pe.edu.upc.ferreshop.entities.Product;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value="SELECT * FROM orders WHERE  price=10", nativeQuery = true)
    List<Order> findByPriceSQL(Long price);

}
