package pe.edu.upc.ferreshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.ferreshop.entities.Order;
import pe.edu.upc.ferreshop.entities.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value="SELECT * FROM orders WHERE  price=10", nativeQuery = true)
    List<Order> findByPriceSQL(Long price);

    @Query("FROM Order o WHERE o.product.id = :id OR LOWER(o.product.name) LIKE %:fullname% OR LOWER(o.product.name) LIKE %:fullname%")
    List<Order> search(@Param("id") String id, @Param("fullname") String fullname);


    @Query("FROM Order o WHERE o.orderDate BETWEEN :datestart AND :dateend")
    List<Order> searchByDates(@Param("datestart") LocalDateTime datestart, @Param("dateend") LocalDateTime dateend);

    @Query(value = "select * from fn_reporte_cantidad_ordenes_fecha()", nativeQuery = true)
    List<Object[]> callProcedureOrFunction();

}
