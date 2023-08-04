package lk.epic.restfulAPI.config.filter;

import lk.epic.restfulAPI.config.service.JwtService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Component// Give this Constructor for, final fields (eg:- private final String myName)
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Implement JWT Authentication Filter
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwt_token;
        final String email;

        // Define a CountDownLatch with an initial count of 1
        CountDownLatch latch = new CountDownLatch(1);

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            // Continue the filter chain
            filterChain.doFilter(request, response);
            return;
        }

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

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            // The next step is to validate and check if the jwt token is still valid or not.
            if (jwtService.isTokenValid(jwt_token, userDetails)) {
                System.out.println("Token is valid");
                // If the jwt token is valid, then we need to update the security context holder and send the request to our dispatcher servlet
                // We need this UsernamePasswordAuthenticationToken Object to update the security context holder
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Build the details out of our Requests out of our HTTP Request
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Final step is to, update the Security Context holder
                // Set Authentication with our authentication token
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // Inside a method
                System.out.println("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication().getName());
                System.out.println("User roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            }
        }
        filterChain.doFilter(request, response);
    }
}
