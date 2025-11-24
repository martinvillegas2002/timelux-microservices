package cl.duoc.auth_service.repository;

import cl.duoc.auth_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// <Usuario, Long> significa: Maneja la entidad "Usuario" y su ID es de tipo "Long"
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // ¡Magia! Spring crea el SQL automáticamente solo al ver el nombre del método.
    // Esto es vital para el Login: "SELECT * FROM usuarios WHERE email = ?"
    Optional<Usuario> findByEmail(String email);
    
    // Para verificar si existe antes de registrar
    boolean existsByEmail(String email);
}