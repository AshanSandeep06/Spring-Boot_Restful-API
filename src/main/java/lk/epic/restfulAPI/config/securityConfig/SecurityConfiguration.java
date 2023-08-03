package lk.epic.restfulAPI.config.securityConfig;

import lk.epic.restfulAPI.config.filter.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Enables Web Spring security
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfiguration(AuthenticationProvider authenticationProvider, JWTAuthenticationFilter jwtAuthFilter, AccessDeniedHandler accessDeniedHandler) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }
    // At the application startup, Spring Security will try to look for
    // Bean type SecurityFilterChain
    // This SecurityFilterChain is the been responsible for Configuring all the HTTP Security of our application
    // Let's start to configure our HTTP Security

    /*
    *  Here's a quick recap of your configuration:

                    Endpoints /api/v1/signup/** and /api/v1/login/** are permitted without authentication.
                    Endpoint /api/v1/movie/update requires users to have the "ADMIN" role.
                    Endpoint /api/v1/movie/delete/{imdb} also requires users to have the "ADMIN" role.
                    All other requests require authentication.
    * */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configurations
        // This will disable the csrf verification
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/api/v1/movie/update"),
                        new AntPathRequestMatcher("/api/v1/movie/delete/{imdb}")
                )
                .hasAuthority("ADMIN")
                .requestMatchers(
                        new AntPathRequestMatcher("/api/v1/signup/**"),
                        new AntPathRequestMatcher("/api/v1/login/**")
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        return httpSecurity.build();
    }
}
