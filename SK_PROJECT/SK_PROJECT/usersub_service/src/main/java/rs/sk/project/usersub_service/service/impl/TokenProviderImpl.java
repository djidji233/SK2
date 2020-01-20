package rs.sk.project.usersub_service.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.sk.project.usersub_service.service.TokenProvider;

import java.util.function.Function;

@Service
public class TokenProviderImpl implements TokenProvider {

    private final String key;

    public TokenProviderImpl(@Value("${security.key}") String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    @Override
    public String decrypt(String token) {
        Function<Claims, String> payloadLoader = Claims::getSubject;
        return payloadLoader.apply(parseClaims(token));
    }

    private Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

}
