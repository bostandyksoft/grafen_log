package com.avmakarov.school.config;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = userService.findByLogin(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException(username);
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user.get().getTeacher() != null) {
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            }
            if (user.get().isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_CLASS_EDITOR"));
                authorities.add(new SimpleGrantedAuthority("ROLE_SUBJECT_EDITOR"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER_EDITOR"));
                authorities.add(new SimpleGrantedAuthority("ROLE_USER_EDITOR"));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.get().getLogin(),
                    user.get().getPassword(),
                    user.get().isActive(),
                    true,
                    true,
                    true,
                    authorities
            );
        };
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .rememberMe(rememberMe -> rememberMe
                        .key("LRM")
                        .tokenValiditySeconds(86400) // 24 часа
                        .userDetailsService(userDetailsService()))
                .formLogin(login ->
                        login
                                .loginPage("/login.html")
                                .loginProcessingUrl("/perform_login")
                                .defaultSuccessUrl("/index.html", false)
                                .permitAll()
                )
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/login.html", "/src/extjs/**", "/src/app/common.js", "/src/app/login/**", "/app/login/**", "/perform_login").permitAll()
                                .requestMatchers("/app/class", "/app/class/**").hasRole("CLASS_EDITOR")
                                .requestMatchers("/app/subject", "/app/subject/**").hasRole("SUBJECT_EDITOR")
                                .requestMatchers("/app/teacher", "/app/teacher/**").hasRole("TEACHER_EDITOR")
                                .requestMatchers("/app/user", "/app/user/**").hasRole("USER_EDITOR")
                                .requestMatchers("/app/lesson", "/app/lesson/**").hasRole("TEACHER")
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
