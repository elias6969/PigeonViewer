package com.mrrice.movieflix.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (esp. for APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register").permitAll() // Allow unauthenticated access
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Use basic auth for everything else

        return http.build();
    }
}
