package cl.duoc.auth_service.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios") // El nombre exacto de tu tabla en MySQL
@Data // Lombok crea los Getters, Setters y toString automáticamente
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nombre;

    @Column(nullable = false)
    private String rol; // 'ADMIN', 'CLIENTE', 'VENDEDOR'
}