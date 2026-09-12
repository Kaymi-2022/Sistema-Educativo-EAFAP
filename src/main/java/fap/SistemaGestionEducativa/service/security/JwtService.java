package fap.SistemaGestionEducativa.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {
    private final SecretKey signingKey;
    private final long expirationMs;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiration-ms}") long expirationMs) {
        if (secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("app.jwt.secret debe tener al menos 32 caracteres.");
        }
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.startsWith("ROLE_") ? role.substring(5) : role)
                .toList();
        Date issuedAt = new Date();
        return Jwts.builder().subject(userDetails.getUsername())
                .claim("username", userDetails.getUsername()).claim("roles", roles)
                .issuedAt(issuedAt).expiration(new Date(issuedAt.getTime() + expirationMs))
                .signWith(signingKey).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return userDetails.getUsername().equalsIgnoreCase(extractUsername(token))
                && extractClaim(token, Claims::getExpiration).after(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser().verifyWith(signingKey).build()
                .parseSignedClaims(token).getPayload();
        return resolver.apply(claims);
    }
}
