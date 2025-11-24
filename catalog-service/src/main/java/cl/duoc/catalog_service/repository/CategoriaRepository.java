package cl.duoc.catalog_service.repository;

import cl.duoc.catalog_service.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // No necesitamos métodos extra por ahora, el CRUD básico ya viene incluido.
}