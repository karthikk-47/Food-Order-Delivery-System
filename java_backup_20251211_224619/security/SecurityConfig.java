/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.servlet.Filter
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.security.authentication.AuthenticationManager
 *  org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
 *  org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
 *  org.springframework.security.config.annotation.web.builders.HttpSecurity
 *  org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 *  org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer$AuthorizedUrl
 *  org.springframework.security.config.http.SessionCreationPolicy
 *  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.security.web.AuthenticationEntryPoint
 *  org.springframework.security.web.SecurityFilterChain
 *  org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 *  org.springframework.web.cors.CorsConfiguration
 *  org.springframework.web.cors.CorsConfigurationSource
 *  org.springframework.web.cors.UrlBasedCorsConfigurationSource
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.security.JwtAuthenticationEntryPoint;
import com.foodapp.deliveryexecutive.security.JwtAuthenticationFilter;
import com.foodapp.deliveryexecutive.security.JwtTokenProvider;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true, securedEnabled=true)
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
        http.cors(cors -> cors.configurationSource((CorsConfigurationSource)this.corsConfigurationSource())).csrf(csrf -> csrf.disable()).exceptionHandling(exception -> exception.authenticationEntryPoint((AuthenticationEntryPoint)this.jwtAuthenticationEntryPoint)).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[]{"/api/auth/**"})).permitAll().requestMatchers(new String[]{"/api/delivery-executive/register", "/api/delivery-executive/login"})).permitAll().requestMatchers(new String[]{"/api/homemaker/register", "/api/homemaker/login"})).permitAll().requestMatchers(new String[]{"/api/user/register", "/api/user/login"})).permitAll().requestMatchers(new String[]{"/api/admin/login"})).permitAll().requestMatchers(new String[]{"/api/webhooks/**"})).permitAll().requestMatchers(new String[]{"/api/payments/webhook/**"})).permitAll().requestMatchers(new String[]{"/api/order-payments/**"})).hasAnyRole(new String[]{"USER", "ADMIN"}).requestMatchers(new String[]{"/api/withdrawals/**"})).hasAnyRole(new String[]{"DELIVERYEXECUTIVE", "HOMEMAKER"}).requestMatchers(new String[]{"/api/delivery-executive/**"})).hasRole("DELIVERYEXECUTIVE").requestMatchers(new String[]{"/api/homemaker/**"})).hasRole("HOMEMAKER").requestMatchers(new String[]{"/api/homemakers/**"})).hasAnyRole(new String[]{"USER", "ADMIN", "HOMEMAKER"}).requestMatchers(new String[]{"/api/user/**"})).hasRole("USER").requestMatchers(new String[]{"/api/users/**"})).hasAnyRole(new String[]{"USER", "ADMIN"}).requestMatchers(new String[]{"/api/admin/**"})).hasRole("ADMIN").requestMatchers(new String[]{"/api/orders/**"})).hasAnyRole(new String[]{"DELIVERYEXECUTIVE", "HOMEMAKER", "USER", "ADMIN"}).requestMatchers(new String[]{"/api/menu-items/**"})).hasAnyRole(new String[]{"HOMEMAKER", "USER", "ADMIN"}).requestMatchers(new String[]{"/api/cart/**"})).hasRole("USER").requestMatchers(new String[]{"/api/wallet/**"})).hasAnyRole(new String[]{"DELIVERYEXECUTIVE", "HOMEMAKER"}).requestMatchers(new String[]{"/api/ratings/**"})).hasAnyRole(new String[]{"DELIVERYEXECUTIVE", "HOMEMAKER", "USER"}).requestMatchers(new String[]{"/api/profile/**"})).hasAnyRole(new String[]{"DELIVERYEXECUTIVE", "HOMEMAKER", "USER"}).anyRequest()).authenticated());
        http.addFilterBefore((Filter)this.authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.valueOf(true));
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(Long.valueOf(3600L));
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
