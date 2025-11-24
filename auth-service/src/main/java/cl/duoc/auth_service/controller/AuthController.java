package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.entity.Usuario;
import cl.duoc.auth_service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.auth_service.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // Esta es la raíz de la URL para este servicio
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectado desde SecurityConfig


    // Endpoint: POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
    
    // Endpoint: POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Buscar usuario
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(request.email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // 2. Verificar contraseña
            if (passwordEncoder.matches(request.password, usuario.getPassword())) {
                
                // 3. Generar Token
                String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
                
                // 4. Devolver Token
                return ResponseEntity.ok(new LoginResponse(token));
            }
        }
        
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}



//clase pequeña para recibir los datos del login (PODEMOS CREARLA APARTE)
// DTO para recibir datos
class LoginRequest {
    public String email;
    public String password;
}

class LoginResponse {
    public String token;
    public LoginResponse(String token) { this.token = token; }
}