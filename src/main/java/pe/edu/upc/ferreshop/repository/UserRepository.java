package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ferreshop.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmailAndPassword(String email, String password);
}
