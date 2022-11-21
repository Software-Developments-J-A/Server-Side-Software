package pe.edu.upc.ferreshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ferreshop.entities.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
