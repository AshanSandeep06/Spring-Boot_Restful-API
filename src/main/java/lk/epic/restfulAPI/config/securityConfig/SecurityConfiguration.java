package lk.epic.restfulAPI.config.securityConfig;

import lk.epic.restfulAPI.config.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity // Enables Web Spring security
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTAuthenticationFilter jwtAuthFilter;

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
                .requestMatchers(
                        new AntPathRequestMatcher("/api/v1/login/**"),
                        new AntPathRequestMatcher("/api/v1/signup/**"),
                        new AntPathRequestMatcher("/api/v1/movie/**")
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
