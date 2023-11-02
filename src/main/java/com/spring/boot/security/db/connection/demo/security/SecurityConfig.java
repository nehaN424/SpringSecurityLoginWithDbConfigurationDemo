package com.spring.boot.security.db.connection.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        // don' use csrf disable for production, this is added here for testing.
       http.csrf(csrf->csrf.disable());

        http.authorizeHttpRequests((request)->{
            request.requestMatchers("/balance","/card").hasAuthority("User");
            request.requestMatchers("/accounts").hasAuthority("Admin");
            request.requestMatchers("/api/users/**").hasAuthority("Admin");
            request.anyRequest().permitAll();
        });

        http.formLogin(Customizer.withDefaults()); //login with browser and form
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}