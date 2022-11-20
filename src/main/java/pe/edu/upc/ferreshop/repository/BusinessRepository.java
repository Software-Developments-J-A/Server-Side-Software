package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Business;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.entities.User;
import java.util.List;
public interface BusinessRepository extends JpaRepository<Business,Long> {


    @Query("SELECT c FROM Business c JOIN  c.user t WHERE c.user.id=?1")
    Business findBusinessByUserIdJPQL(Long userId);
}
