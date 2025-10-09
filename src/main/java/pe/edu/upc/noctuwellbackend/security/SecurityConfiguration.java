package pe.edu.upc.noctuwellbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    // Swagger abierto
    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**"
    };

    // Endpoints que quieres probar sin token (ajusta si hace falta)
    private static final String[] PUBLIC_API = {
            "/upc/users/login/**",
            "/upc/users/register/**",
            "/plans/**",
            "/pacientes/**",
            "/especialistas/**",
            "/horarios/**",
            "/tipoespecialistas/**",
            "/reseñas/**",   // con ñ
            "/resenas/**"    // sin ñ (por si cambias la ruta)
    };

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // Authentication Manager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    // Security Filter Chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)           // necesario para POST/PUT/DELETE desde Swagger
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger sin auth
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // Preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Tus controladores abiertos para probar
                        .requestMatchers(PUBLIC_API).permitAll()

                        // Reglas específicas que ya tenías:
                        .requestMatchers(HttpMethod.GET,  "/upc/faculties/**").hasAnyAuthority("ROLE_USER", "ROLE_ASSIST", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/upc/faculties/**").hasAnyAuthority("ROLE_ASSIST", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/upc/faculties/**").hasAnyAuthority("ROLE_ASSIST", "ROLE_ADMIN")
                        .requestMatchers("/upc/faculties/**").hasAnyAuthority("ROLE_ADMIN")

                        // resto protegido
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
