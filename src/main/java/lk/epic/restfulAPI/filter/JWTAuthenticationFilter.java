package lk.epic.restfulAPI.filter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Implement JWT Authentication Filter
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwt_token;

        if (authenticationHeader == null || authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        } else {
            jwt_token = authenticationHeader.substring(7);
            System.out.println(jwt_token);


        }
    }
}
