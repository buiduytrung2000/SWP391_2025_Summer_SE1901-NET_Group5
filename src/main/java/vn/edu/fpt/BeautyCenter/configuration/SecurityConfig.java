package vn.edu.fpt.BeautyCenter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**", "/home", "/js/**", "/css/**", "/images/**", "/fonts/**", "/plugins/**").permitAll()

                )
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/requests", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/error"));
        return http.build();
    }
}
