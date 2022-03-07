package com.enmivida.rest.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username, List<String> roles) throws IllegalArgumentException, JWTCreationException {
        Date now = new Date();
        Date validity = new Date(now.getTime()+(expiration*1000));
        return JWT.create()
                .withSubject("User Details")
                .withClaim("username", username)
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveUsernameSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .acceptExpiresAt(expiration) // en segundos
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
