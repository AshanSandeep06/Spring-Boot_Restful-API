package lk.epic.restfulAPI.config.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Enables Web Spring security
@RequiredArgsConstructor
public class SecurityConfiguration {
    // At the application startup, Spring Security will try to look for
    // Bean type SecurityFilterChain
    // This SecurityFilterChain is the been responsible for Configuring all the HTTP Security of our application
    // Let's start to configure our HTTP Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configurations
        // This will disable the csrf verification
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated();

        return httpSecurity.build();
    }
}
