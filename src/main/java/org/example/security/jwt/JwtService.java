package org.example.security.jwt;
import org.example.data.models.User;

public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
    boolean validateToken(String token, User user);
}
