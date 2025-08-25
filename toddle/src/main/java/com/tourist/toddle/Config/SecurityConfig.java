package com.tourist.toddle.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        
        http
            .sessionManagement(management -> management
                                            .sessionCreationPolicy(SessionCreationPolicy
                                                .STATELESS))
            .authorizeHttpRequests(Authorize -> Authorize
                                    .requestMatchers("/api/**")
                                    .authenticated()
                                    .anyRequest()
                                    .permitAll())
                                .addFilterBefore(new jwtVal(), BasicAuthenticationFilter.class)
                                .csrf(csrf -> csrf.disable());
        
        return http.build();
    } 

    @Bean
    PasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }
}
