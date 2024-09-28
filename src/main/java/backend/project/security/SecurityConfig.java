package backend.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desactivar temporalmente la seguridad
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();  // Permitir todas las solicitudes sin autenticación
        return http.build();
    }
}
