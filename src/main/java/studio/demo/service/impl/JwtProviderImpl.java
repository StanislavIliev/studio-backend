package studio.demo.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import studio.demo.model.service.UserServiceModel;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import java.util.UUID;

@Service
public class JwtProviderImpl {

     private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512

    public String createJwt(UserServiceModel user) {
        String jwtToken = Jwts.builder()
                .claim("authorities", new ArrayList<>())
                .setSubject("joe")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 432_000_000))
                .signWith(key)
                .compact();
        return jwtToken;
    }
    public String decodeJwt(String token){
        Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
        System.out.println();
        return null;
    }

}
