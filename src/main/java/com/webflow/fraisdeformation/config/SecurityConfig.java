package com.webflow.fraisdeformation.config;

import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.repository.UtilisateurRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilisation de l'algorithme BCrypt pour chiffrer les mots de passe
    }

    @Bean
    public UserDetailsService userDetailsService(UtilisateurRepository utilisateurRepository) {
        return email -> {
            Utilisateur user = utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new User(user.getEmail(), user.getMotDePasse(), authorities);
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Désactiver CSRF pour simplifier les tests (à activer en production)
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()  // Autoriser l'accès public à la page d'accueil et aux ressources statiques
                .antMatchers("/declaration-flow/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()  // Autoriser l'accès à la console H2
                .antMatchers("/api/**").hasAnyRole("ROLE_PROFESSEUR", "ROLE_ADMIN")  // Restreindre l'accès aux APIs pour les utilisateurs authentifiés
                .antMatchers("/admin/**").hasRole("ADMIN")  // Réserver l'accès aux administrateurs uniquement
                .anyRequest().authenticated()  // Authentification requise pour les autres routes
                .and()
                .headers().frameOptions().sameOrigin()  // Permet l'affichage de la console H2 dans un iframe
                .and()
                .formLogin().loginPage("/login").permitAll()  // Page de connexion accessible à tous
                .defaultSuccessUrl("/home", true)  // Redirection après connexion
                .and()
                .logout().logoutSuccessUrl("/").permitAll();  // Redirection après déconnexion
    }
}
