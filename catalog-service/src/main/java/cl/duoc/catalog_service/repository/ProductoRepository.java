package cl.duoc.catalog_service.repository;

import cl.duoc.catalog_service.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Método útil: Buscar productos por el ID de su categoría
    // SQL: SELECT * FROM productos WHERE categoria_id = ?
    List<Producto> findByCategoriaId(Long categoriaId);
}