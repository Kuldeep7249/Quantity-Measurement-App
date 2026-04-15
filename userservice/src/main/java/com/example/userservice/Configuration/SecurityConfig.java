package com.example.userservice.Configuration;

import com.example.userservice.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors->{})
                .csrf(csrf -> csrf.disable())
                // Allow sessions for OAuth state (Stateless breaks OAuth2 login)

                .authorizeHttpRequests(auth -> auth
                        // Public routes — no token needed
                        .requestMatchers(
                                "/users/login",
                                "/users/signup",
                                "/login/oauth2/**",           // Google callback
                                "/oauth2/authorization/**" // Google redirect initiation — was missing!

                        ).permitAll()
                        .requestMatchers("/users/**").permitAll()   // ✅ ADD THIS
                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth -> oauth
                        .successHandler(oAuth2SuccessHandler)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                // JWT filter runs before Spring's auth filter

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}