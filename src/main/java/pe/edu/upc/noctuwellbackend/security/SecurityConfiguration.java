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

    private static final String[] AUTH_WHITELIST ={
            // -- Swagger
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",

            // -- Login
            "/upc/users/login/**",

            // -- Resigtro
            "/upc/users/register/**"
    };

    // Authentication Manager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
    }

    // Security Filter Chain

    /*

    1. Cuales van a ser los Request(pedidos) que seran evaluados para saber si el usuario tiene permisos sobre estos
        a. AnyRequest -> Todos los pedidos
        b. RequestMatchers -> Se evalua solo los que coincidan con las rutas
        c. RequestMatches + HttpMethod -> Se evalua a los que coincidan con la ruta y con el metodo (GET, POST, etc.)

    2. Cual es la regla de autorización que se van a aplicar sobre estos Request(pedidos)
        a. permitAll()
        b. denyAll()
        c. requestMatchers()
        d. hasRole()
        e. hayAnyRole()
        f. hasAuthority()
        g. hasAnyAuthority()
        h. SpEL Spring Expression Language
        i. authenticated()

    */

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore( jwtRequestFilter , UsernamePasswordAuthenticationFilter.class );
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests( (auth) -> auth
               // .anyRequest().permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                //Poner una cadena de filtros iniciando por el más especifico hacia el más genérico
                .requestMatchers(HttpMethod.GET, "/upc/faculties/**").hasAnyAuthority("ROLE_USER", "ROLE_ASSIST", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/faculties/**").hasAnyAuthority("ROLE_ASSIST", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/faculties/**").hasAnyAuthority("ROLE_ASSIST", "ROLE_ADMIN")
                .requestMatchers("/upc/faculties/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        );

        http.sessionManagement(
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }



    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
