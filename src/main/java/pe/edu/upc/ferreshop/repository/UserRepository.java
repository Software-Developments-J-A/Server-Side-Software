package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ferreshop.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
    List<User>findByName(String name);
}
