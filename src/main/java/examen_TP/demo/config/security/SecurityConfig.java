package examen_TP.demo.config.security;

import examen_TP.demo.config.security.utils.JwtAuthentificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthentificationFilter jwtAuthentificationFilter;;

    private static final String[] WHITE_LIST_URL = {

            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/js/**",
            "/css/**",
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // Enable CORS
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/auth/create").permitAll()
                                .requestMatchers("/api/v1/auth/update").hasRole("ADMIN")
                                .requestMatchers("/api/v1/auth/delete/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/auth/getAll").hasRole("ADMIN")
                                .requestMatchers("/api/v1/auth/login").permitAll()
                                .requestMatchers("/api/test/test").hasRole("ADMIN")
                                .requestMatchers("/api/v1/role/create").permitAll()
                                .requestMatchers("/api/v1/role/update/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/role/delete/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/role/getAll").hasRole("ADMIN")
                                .requestMatchers("/api/v1/message/create").hasRole("USER")
                                .requestMatchers("/api/v1/message/update").hasRole("USER")
                                .requestMatchers("/api/v1/message/delete/**").hasRole("USER")
                                .requestMatchers("/api/v1/message/getAll").hasRole("ADMIN")
                                .requestMatchers("/api/v1/message/filterByKeyWord").hasRole("ADMIN")
                                .requestMatchers("/api/v1/message/getAllMessagesByLimit").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
