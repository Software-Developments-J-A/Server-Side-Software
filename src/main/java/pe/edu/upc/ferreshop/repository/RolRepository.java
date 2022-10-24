package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ferreshop.entities.Rol;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Long> {

    List<Rol> findByNameContaining (String name);

}
