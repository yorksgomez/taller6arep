package edu.eci.arep;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenManager {
    private static final String SECRET_KEY = "xyz123";
    
    public static String generateToken(String userId) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                  .withClaim("userId", userId)
                  .sign(algorithm);
    }

    public static String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                      .build()
                      .verify(token)
                      .getClaim("userId")
                      .asString();
        } catch (Exception e) {
            return null; // Token no válido
        }
    }
}