package cl.duoc.auth_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso total a las rutas de autenticación (login/register)
                .requestMatchers("/api/auth/**").permitAll()
                // --- NUEVAS: Permisos para Swagger ---
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                // Cualquier otra cosa requiere autenticación (aunque el Gateway manejará esto)
                .anyRequest().authenticated()
            );
        
        return http.build();
    }

    // Exponemos el encriptador para poder usarlo en el Service
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*Por defecto, Spring Security bloquea TODO.
 Tenemos que decirle: "Deja pasar a cualquiera que
  quiera entrar a /api/auth/** (login y registro),
   pero bloquea lo demás".*/