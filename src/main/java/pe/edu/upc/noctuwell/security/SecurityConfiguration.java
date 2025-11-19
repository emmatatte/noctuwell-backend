package pe.edu.upc.noctuwell.security;

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
                .anyRequest().permitAll()
//                .requestMatchers(AUTH_WHITELIST).permitAll()


//                // Especialistas
//                .requestMatchers(HttpMethod.GET, "/noctuwell/specialists/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PATIENT", "ROLE_SPECIALIST")
//                .requestMatchers(HttpMethod.POST, "/noctuwell/specialists/**").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/noctuwell/specialists/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SPECIALIST")
//                .requestMatchers(HttpMethod.DELETE, "/noctuwell/specialists/**").hasAuthority("ROLE_ADMIN")
//
//                // Pacientes
//                .requestMatchers(HttpMethod.GET, "/noctuwell/patients/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SPECIALIST")
//                .requestMatchers(HttpMethod.POST, "/noctuwell/patients/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SPECIALIST")
//                .requestMatchers(HttpMethod.PUT, "/noctuwell/patients/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PATIENT")
//                .requestMatchers(HttpMethod.DELETE, "/noctuwell/patients/**").hasAuthority("ROLE_ADMIN")
//
//                // Citas
//                .requestMatchers("/noctuwell/appointments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SPECIALIST", "ROLE_PATIENT")
//
//                // Diagnósticos
//                .requestMatchers("/noctuwell/diagnoses/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SPECIALIST")
//
//                // Historias
//                .requestMatchers("/noctuwell/histories/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PATIENT", "ROLE_SPECIALIST")
//
//                // Mensajes
//                .requestMatchers("/noctuwell/messages/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PATIENT", "ROLE_SPECIALIST")
//                .anyRequest().authenticated()
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
