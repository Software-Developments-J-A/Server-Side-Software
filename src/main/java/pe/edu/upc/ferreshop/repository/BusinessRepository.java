package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.entities.User;
import java.util.List;
public interface BusinessRepository extends JpaRepository<Business,Long> {

}
