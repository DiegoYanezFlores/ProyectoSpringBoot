package ec.com.quitomarket.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Aquí inyectarías tu UsuarioRepository real conectado a PostgreSQL:
    // private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // SIMULACIÓN PEDAGÓGICA (Reemplazar con tu consulta a repositorio)
        if ("coordinador_quito".equals(username)) {
            // "Quito2026" encriptado en BCrypt
            String passwordEncriptado = "$2a$10$X5pGZ893e9vK/b77P6MhI.gY4R7hVpCjS9f9Wv3e4kYVf7g2m5CqG";

            return new User(
                    username,
                    passwordEncriptado,
                    Collections.emptyList() // Aquí irían los roles/permisos
            );
        }
        throw new UsernameNotFoundException("Usuario no encontrado en PostgreSQL: " + username);
    }
}