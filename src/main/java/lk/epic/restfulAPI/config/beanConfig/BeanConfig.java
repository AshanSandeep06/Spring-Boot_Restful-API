package lk.epic.restfulAPI.config.beanConfig;

import lk.epic.restfulAPI.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class BeanConfig {
    private final UserRepo userRepo;

    @Autowired
    public BeanConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (String email) -> userRepo.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*---- BCrypt Hashing algo ----*/
        // return new BCryptPasswordEncoder();

        /*---- SHA256 Hashing algo ----*/
        // return new StandardPasswordEncoder();

        /*---- SHA256 Hashing algo ----*/
        return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            // Create a JSON response with an error message
            String jsonErrorResponse = "{\"responseCode\": \"06\",\"responseMsg\": \"Access denied: You are not authorized to access this resource.\",\"content\": null}";

            try (PrintWriter writer = response.getWriter()) {
                writer.write(jsonErrorResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
