package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Order;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.entities.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Long> {

    @Query(value="SELECT * FROM status WHERE  name='proceso'", nativeQuery = true)
    List<Status> findByNameSQL(String status);

    @Query("SELECT o FROM Order o WHERE o.status ='proceso'")
    List<Order> findByStatusJPQL(String status);


}
