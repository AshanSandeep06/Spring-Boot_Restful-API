package lk.epic.restfulAPI.config.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service // To tell Spring, create a bean from this class
public class JwtService {
    @Value("${secret_key}")
    private static String SECRET_KEY;

    // A Sign-in key is a Secret key, that is used to
    // To digitally sign the jwt token

    // The Signing Key is used to create the Signature part of the JWt token
    // Which is used to verify the sender of the jwt token,
    // who it claims to be and ensure that the message wasn't changed

    // So want to ensure that the Same person or same client
    // that is sending this JWT Key is the one that claims who to be.

    // The Sign-in key is used in conjunction with the sign-in algorithm
    // specified in the JWT Header to create the signature

    // parseClaimsJws ---> This method is used to parse the JWT Token
    // Once the Token is Parsed then we can call getBody() method
    // To get all the Claims within this token.
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        // Once the Secret key was Decoded(Like Decrypted)
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // hmacShaKey===> this is the Sign-in algorithm
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // To Extract a single Claim
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        // Extracting all the Claims of Jwt token
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    // The Subject is email or username of My User
    // Claims::getSubject ----> This is the Subject of the jwt token
    public String extractEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    // We also need to implement methods for,
    // Generate a jwt token,
    // Check the jwt token is expired or not


}
