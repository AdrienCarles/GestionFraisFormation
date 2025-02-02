package com.webflow.fraisdeformation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilisation de l'algorithme BCrypt pour chiffrer les mots de passe
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Désactiver CSRF pour simplifier les tests (à activer en production)
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/js/**", "/images/**", "/register").permitAll()  // Autoriser l'accès public à la page d'accueil et aux ressources statiques
                .antMatchers("/h2-console/**").permitAll()  // Autoriser l'accès à la console H2
                .antMatchers("/api/**").hasAnyRole("PROFESSEUR", "ADMIN")  // Restreindre l'accès aux APIs pour les utilisateurs authentifiés
                .antMatchers("/admin/**").hasRole("ADMIN")  // Réserver l'accès aux administrateurs uniquement
                .anyRequest().authenticated()  // Authentification requise pour les autres routes
                .and()
                .headers().frameOptions().sameOrigin()  // Permet l'affichage de la console H2 dans un iframe
                .and()
                .formLogin().loginPage("/login").permitAll()  // Page de connexion accessible à tous
                .defaultSuccessUrl("/home", true)  // Redirection après connexion
                .and()
                .logout().logoutSuccessUrl("/home").permitAll();  // Redirection après déconnexion
    }
}
