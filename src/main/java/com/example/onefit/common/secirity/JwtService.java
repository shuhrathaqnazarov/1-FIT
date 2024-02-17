package com.example.onefit.common.secirity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.token.secret}")
    private String secret;

    @Value("${security.token.expiration}")
    private Integer expirationHours;


    public String generateToken(String phoneNumber) {
        Instant now = Instant.now();
        Instant expire = now.plus(expirationHours, ChronoUnit.HOURS);

        return Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expire))
                .setSubject(phoneNumber)
                .signWith(signKey())
                .compact();
    }

    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }


    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
