package lk.epic.restfulAPI.config.filter;

import lk.epic.restfulAPI.config.service.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor // Give this Constructor for, final fields (eg:- private final String myName)
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Implement JWT Authentication Filter
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwt_token;
        final String email;

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        } else {
            // Extract the JWT Token from Authentication Header
            jwt_token = authenticationHeader.substring(7);
            System.out.println(jwt_token);

            // After Extracting the JWT Token, Then I want to extract
            // also this User Email

            // We need to extract User Email from the JWT Token,
            // We need a class that can manipulate this JWT Token
            // And we need to pass JWT Token as a parameter
            // To JWTService
            email = jwtService.extractEmail(jwt_token);
        }
    }
}
