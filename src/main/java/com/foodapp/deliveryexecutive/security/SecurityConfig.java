package com.foodapp.deliveryexecutive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtTokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilter() {
        return new JwtAuthenticationFilter(this.tokenProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger/OpenAPI endpoints - PUBLIC ACCESS
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-resources/**",
                                "/webjars/**")
                        .permitAll()

                        // Actuator endpoints - ADMIN ONLY
                        .requestMatchers("/actuator/**").hasRole("ADMIN")

                        // WebSocket endpoints
                        .requestMatchers("/ws/**").permitAll()

                        // Auth endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/getAddress").permitAll()
                        .requestMatchers("/api/delivery-executive/register", "/api/delivery-executive/login")
                        .permitAll()
                        .requestMatchers("/api/homemaker/register", "/api/homemaker/login").permitAll()
                        .requestMatchers("/api/user/register", "/api/user/login").permitAll()
                        .requestMatchers("/api/admin/login").permitAll()

                        // Webhooks
                        .requestMatchers("/api/webhooks/**").permitAll()
                        .requestMatchers("/api/payments/webhook/**").permitAll()

                        // Role-based access
                        .requestMatchers("/api/order-payments/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/withdrawals/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER")
                        .requestMatchers("/api/delivery-executive/**").hasRole("DELIVERYEXECUTIVE")
                        .requestMatchers("/api/homemaker/**").hasRole("HOMEMAKER")
                        .requestMatchers("/api/homemakers/**").hasAnyRole("USER", "ADMIN", "HOMEMAKER")
                        .requestMatchers("/api/user/**").hasRole("USER")
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/orders/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER", "USER", "ADMIN")
                        .requestMatchers("/api/menu-items/**").hasAnyRole("HOMEMAKER", "USER", "ADMIN")
                        .requestMatchers("/api/cart/**").hasRole("USER")
                        .requestMatchers("/api/wallet/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER")
                        .requestMatchers("/api/bank-accounts/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER")
                        .requestMatchers("/api/ratings/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER", "USER")
                        .requestMatchers("/api/profile/**").hasAnyRole("DELIVERYEXECUTIVE", "HOMEMAKER", "USER")
                        .requestMatchers("/api/external/restaurants/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/notifications/**").authenticated()
                        .anyRequest().authenticated());

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
