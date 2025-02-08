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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UtilisateurRepository utilisateurRepository) {
        return email -> {
            Utilisateur user = utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getRoleName(user.getRole()));

            return new User(user.getEmail(), user.getMotDePasse(), List.of(authority));
        };
    }

    private String getRoleName(int role) {
        switch (role) {
            case 1: return "ROLE_PROFESSEUR";
            case 2: return "ROLE_ADMIN";
            default: return "ROLE_USER"; // Rôle par défaut
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Désactiver CSRF pour des tests uniquement
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/declaration-flow/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/**").hasAnyAuthority("ROLE_PROFESSEUR", "ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home").permitAll();
    }
}
