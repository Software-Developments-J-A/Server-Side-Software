package pe.edu.upc.ferreshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ferreshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
