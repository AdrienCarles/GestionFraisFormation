package com.webflow.fraisdeformation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // Autoriser l'accès à la console H2
                .anyRequest().authenticated()  // Authentification requise pour toutes les autres routes
                .and()
                .headers().frameOptions().sameOrigin()  // Autoriser l'affichage de la console H2 dans un iframe
                .and()
                .formLogin().loginPage("/login").permitAll();
    }
}
