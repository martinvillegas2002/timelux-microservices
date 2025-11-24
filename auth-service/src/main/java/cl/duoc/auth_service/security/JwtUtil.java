package cl.duoc.auth_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // Lee la clave secreta de application.properties
    private String secret;

    @Value("${jwt.expiration}") // Lee el tiempo de expiración
    private Long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        // Crea una llave criptográfica segura usando tu secreto
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol) // Guardamos el rol dentro del token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Aquí podrías agregar métodos para validar el token si fuera necesario
}


/*Esta clase es una "herramienta" 
que sabe crear y validar tokens.*/